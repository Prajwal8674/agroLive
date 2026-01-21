package com.prajwal.agrolive.apiFetching.apiController;

import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;
import com.prajwal.agrolive.apiFetching.apiRepository.MarketRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MarketAPIController {

    @Autowired
    private MarketRecordRepository marketRecordRepository;

    @GetMapping("/allCommodities")
    public String getAllCommodities(
            @RequestParam(required = false) String commodity,
            @RequestParam(required = false) String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            Model model
    ) {

        Page<MarketRecord> recordsPage;

        if (commodity != null && !commodity.isEmpty() && state != null && !state.isEmpty()) {
            // Filtered data
            recordsPage = marketRecordRepository
                    .findByCommodityIgnoreCaseAndStateIgnoreCase(
                            commodity, state, PageRequest.of(page, size)
                    );
        } else {
            // All data
            recordsPage = marketRecordRepository.findAll(PageRequest.of(page, size));
        }

        int totalPages = recordsPage.getTotalPages();
        int startPage = Math.max(0, page - 2);
        int endPage = Math.min(totalPages - 1, page + 2);

        model.addAttribute("recordsPage", recordsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // Send filter values back to UI
        model.addAttribute("commodity", commodity);
        model.addAttribute("state", state);

        return "allCommodities";
    }
}
