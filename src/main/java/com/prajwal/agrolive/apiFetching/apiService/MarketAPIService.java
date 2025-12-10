package com.prajwal.agrolive.apiFetching.apiService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;
import com.prajwal.agrolive.apiFetching.apiRepository.MarketRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MarketRecordRepository marketRecordRepository;

    // Replace YOUR_CORRECT_API_KEY with your actual key
    private static final String API_URL =
            "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?" +
            "api-key=579b464db66ec23bdd000001cdc3b564546246a772a26393094f5645" +
            "&format=json" +
            "&limit=10000" +
            "&offset=0";

    public void fetchAndStoreMarketData() {
        try {
            // Fetch data from API
            String response = restTemplate.getForObject(API_URL, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode records = rootNode.path("records");

            if (records.isArray()) {
                for (JsonNode node : records) {
                    String state = node.path("state").asText();
                    String district = node.path("district").asText();
                    String market = node.path("market").asText();
                    String commodity = node.path("commodity").asText();
                    String variety = node.path("variety").asText();
                    String date = node.path("arrival_date").asText();

                    // Skip if this record already exists in DB
                    boolean exists = marketRecordRepository.existsByStateAndDistrictAndMarketAndCommodityAndVarietyAndDate(
                            state, district, market, commodity, variety, date
                    );
                    if (exists) continue;

                    // Create new record and save
                    MarketRecord record = new MarketRecord();
                    record.setState(state);
                    record.setDistrict(district);
                    record.setMarket(market);
                    record.setCommodity(commodity);
                    record.setVariety(variety);
                    record.setMin_price(node.path("min_price").asText());
                    record.setMax_price(node.path("max_price").asText());
                    record.setModal_price(node.path("modal_price").asText());
                    record.setDate(date);

                    marketRecordRepository.save(record);
                }
            }

            System.out.println("All data fetched and inserted into DB successfully!");

        } catch (HttpClientErrorException e) {
            System.err.println("Error fetching data: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            System.err.println("Error fetching or storing data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
