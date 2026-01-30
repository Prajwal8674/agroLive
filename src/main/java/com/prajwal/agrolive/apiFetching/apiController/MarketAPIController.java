package com.prajwal.agrolive.apiFetching.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;
import com.prajwal.agrolive.apiFetching.apiRepository.MarketRecordRepository;
import com.prajwal.agrolive.userEntity.FavoriteCommodity;
import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userServices.FavoriteCommodityService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MarketAPIController {

  @Autowired
  private MarketRecordRepository marketRecordRepository;

  @Autowired
  private FavoriteCommodityService favoriteCommodityService;

  @GetMapping("/allCommodities")
  public String getAllCommodities(
      @AuthenticationPrincipal User user,
      @RequestParam(required = false) String commodity,
      @RequestParam(required = false) String state,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "50") int size,
      Model model) {

    Page<MarketRecord> recordsPage;

    if (commodity != null && !commodity.isEmpty()
        && state != null && !state.isEmpty()) {

      recordsPage =
          marketRecordRepository
              .findByCommodityIgnoreCaseAndStateIgnoreCaseOrderByDateDesc(
                  commodity, state, PageRequest.of(page, size));
    } else {
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

    model.addAttribute("commodity", commodity);
    model.addAttribute("state", state);

    /* ---------------------------------------------------
       FAVORITE STATUS HANDLING (NEW & SAFE)
       --------------------------------------------------- */
    if (user != null) {

      // Fetch ALL favorites of user once
      List<FavoriteCommodity> favorites =
          favoriteCommodityService.getUserFavorites(user);

      // Build a lookup set
      Set<String> favoriteKeySet = new HashSet<>();
      for (FavoriteCommodity fav : favorites) {
        String key =
            fav.getCommodity() + "|" +
            fav.getVariety() + "|" +
            fav.getState() + "|" +
            fav.getDistrict() + "|" +
            fav.getMarket();
        favoriteKeySet.add(key);
      }

      // Map for UI
      Map<String, Boolean> favoriteStatusMap = new HashMap<>();

      for (MarketRecord record : recordsPage.getContent()) {
        String key =
            record.getCommodity() + "|" +
            record.getVariety() + "|" +
            record.getState() + "|" +
            record.getDistrict() + "|" +
            record.getMarket();

        favoriteStatusMap.put(key, favoriteKeySet.contains(key));
      }

      model.addAttribute("favoriteStatusMap", favoriteStatusMap);
    }

    return "allCommodities";
  }
}
