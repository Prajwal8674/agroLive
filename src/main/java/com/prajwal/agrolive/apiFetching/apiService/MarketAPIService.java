package com.prajwal.agrolive.apiFetching.apiService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;
import com.prajwal.agrolive.apiFetching.apiRepository.MarketRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class MarketAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MarketRecordRepository marketRecordRepository;

    private static final String BASE_URL =
            "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070";

    private static final String API_KEY =
            "579b464db66ec23bdd000001cdc3b564546246a772a26393094f5645";

    private static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Runs daily at 6 AM IST
   // @Scheduled(cron = "0 0 6 * * ?", zone = "Asia/Kolkata")
    @Scheduled(cron = "0 * * * * ?", zone = "Asia/Kolkata")  // runs every minute
    @Transactional
    public void fetchLatestMarketData() {
        System.out.println("Scheduled MarketAPIService started");

        int limit = 1000;
        int offset = 0;
        boolean hasMore = true;

        Set<JsonNode> allRecords = new HashSet<>();

        try {
            // 1️⃣ Fetch all pages
            while (hasMore) {
                String apiUrl = BASE_URL +
                        "?api-key=" + API_KEY +
                        "&format=json" +
                        "&limit=" + limit +
                        "&offset=" + offset;

                String response = restTemplate.getForObject(apiUrl, String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode records = mapper.readTree(response).path("records");

                if (!records.isArray() || records.isEmpty()) {
                    hasMore = false;
                    break;
                }

                records.forEach(allRecords::add);
                offset += limit; // next page
            }

            System.out.println("Total records fetched from API: " + allRecords.size());

            String latestDateStr = null;
            LocalDate maxDate = null;
            for (JsonNode node : allRecords) {
                String dateStr = node.path("arrival_date").asText(); // dd/MM/yyyy
                LocalDate date = LocalDate.parse(dateStr, API_DATE_FORMAT);
                if (maxDate == null || date.isAfter(maxDate)) {
                    maxDate = date;
                    latestDateStr = dateStr;
                }
            }

            if (latestDateStr == null) {
                System.out.println("No valid dates found in API response");
                return;
            }

            System.out.println("Latest available API date: " + latestDateStr);

            // 3️⃣ Insert records for latest date only
            Set<String> insertedKeys = new HashSet<>();
            for (JsonNode node : allRecords) {
                String dateStr = node.path("arrival_date").asText();
                if (!latestDateStr.equals(dateStr)) continue;

                String key = node.path("state").asText() + "|" +
                        node.path("district").asText() + "|" +
                        node.path("market").asText() + "|" +
                        node.path("commodity").asText() + "|" +
                        node.path("variety").asText() + "|" +
                        dateStr;

                if (insertedKeys.contains(key)) continue;
                insertedKeys.add(key);

                boolean exists = marketRecordRepository
                        .existsByStateAndDistrictAndMarketAndCommodityAndVarietyAndDate(
                                node.path("state").asText(),
                                node.path("district").asText(),
                                node.path("market").asText(),
                                node.path("commodity").asText(),
                                node.path("variety").asText(),
                                dateStr
                        );
                if (exists) continue;

                MarketRecord record = new MarketRecord();
                record.setState(node.path("state").asText());
                record.setDistrict(node.path("district").asText());
                record.setMarket(node.path("market").asText());
                record.setCommodity(node.path("commodity").asText());
                record.setVariety(node.path("variety").asText());
                record.setMin_price(node.path("min_price").asText());
                record.setMax_price(node.path("max_price").asText());
                record.setModal_price(node.path("modal_price").asText());
                record.setDate(dateStr);

                marketRecordRepository.save(record);
            }

            System.out.println("Inserted market data for date: " + latestDateStr);

            // 4️⃣ Delete records older than last 7 days
            LocalDate threshold = LocalDate.now().minusDays(7);
            String thresholdStr = threshold.format(API_DATE_FORMAT);
            marketRecordRepository.deleteByDateBefore(thresholdStr);
            System.out.println("Deleted records older than: " + thresholdStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
