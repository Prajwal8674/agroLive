package com.prajwal.agrolive.userController;

import com.prajwal.agrolive.userEntity.FavoriteCommodity;
import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userServices.FavoriteCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FavoriteController {

  @Autowired 
  private FavoriteCommodityService favoriteCommodityService;

  /**
   * Toggle favorite status (AJAX endpoint)
   */
  @PostMapping("/api/favorites/toggle")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> toggleFavorite(
      @AuthenticationPrincipal User user,
      @RequestParam String commodity,
      @RequestParam String variety,
      @RequestParam String state,
      @RequestParam String district,
      @RequestParam String market) {

    Map<String, Object> response = new HashMap<>();

    if (user == null) {
      response.put("success", false);
      response.put("message", "Please login to add favorites");
      return ResponseEntity.status(401).body(response);
    }

    try {
      boolean isFavorited = favoriteCommodityService.toggleFavorite(
          user, commodity, variety, state, district, market);

      response.put("success", true);
      response.put("isFavorited", isFavorited);
      response.put("message", isFavorited ? "Added to favorites" : "Removed from favorites");

      return ResponseEntity.ok(response);
      
    } catch (Exception e) {
      response.put("success", false);
      response.put("message", "An error occurred: " + e.getMessage());
      return ResponseEntity.status(500).body(response);
    }
  }

  /**
   * Check if commodity is favorited (AJAX endpoint)
   */
  @GetMapping("/api/favorites/check")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> checkFavorite(
      @AuthenticationPrincipal User user,
      @RequestParam String commodity,
      @RequestParam String variety,
      @RequestParam String state,
      @RequestParam String district,
      @RequestParam String market) {

    Map<String, Object> response = new HashMap<>();

    if (user == null) {
      response.put("isFavorited", false);
      return ResponseEntity.ok(response);
    }

    boolean isFavorited = favoriteCommodityService.isFavorite(
        user, commodity, variety, state, district, market);
    response.put("isFavorited", isFavorited);

    return ResponseEntity.ok(response);
  }

  /**
   * View user's favorite commodities page
   */
  @GetMapping("/favorites")
  public String viewFavorites(@AuthenticationPrincipal User user, Model model) {

    if (user == null) {
      return "redirect:/login";
    }

    List<FavoriteCommodity> favorites = favoriteCommodityService.getUserFavorites(user);
    model.addAttribute("favorites", favorites);
    model.addAttribute("favoritesCount", favorites.size());

    return "favorites";
  }

  /**
   * Remove favorite (alternative endpoint if needed)
   */
  @DeleteMapping("/api/favorites/remove")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> removeFavorite(
      @AuthenticationPrincipal User user,
      @RequestParam String commodity,
      @RequestParam String variety,
      @RequestParam String state,
      @RequestParam String district,
      @RequestParam String market) {

    Map<String, Object> response = new HashMap<>();

    if (user == null) {
      response.put("success", false);
      response.put("message", "Please login");
      return ResponseEntity.status(401).body(response);
    }

    try {
      favoriteCommodityService.removeFavorite(
          user, commodity, variety, state, district, market);

      response.put("success", true);
      response.put("message", "Removed from favorites");

      return ResponseEntity.ok(response);
      
    } catch (Exception e) {
      response.put("success", false);
      response.put("message", "An error occurred: " + e.getMessage());
      return ResponseEntity.status(500).body(response);
    }
  }
}