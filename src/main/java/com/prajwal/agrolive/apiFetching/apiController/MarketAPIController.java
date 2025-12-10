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
    public String getAllCommoditiesPage(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        long totalRecords = marketRecordRepository.count();
        int totalPages = (int) Math.ceil((double) totalRecords / size);

        if (page < 0) page = 0;
        if (page >= totalPages) page = 0;

        Page<MarketRecord> recordsPage = marketRecordRepository.findAll(PageRequest.of(page, size));

        // Determine pagination window (e.g., 5 pages max)
        int startPage = Math.max(0, page - 2);
        int endPage = Math.min(totalPages - 1, page + 2);

        model.addAttribute("recordsPage", recordsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "allCommodities";
    }


}
