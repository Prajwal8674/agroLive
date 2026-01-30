package com.prajwal.agrolive.userServices;

import com.prajwal.agrolive.userEntity.FavoriteCommodity;
import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.FavoriteCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteCommodityService {

  @Autowired 
  private FavoriteCommodityRepository favoriteCommodityRepository;

  /**
   * Add a commodity to user's favorites
   */
  @Transactional
  public boolean addFavorite(
      User user, 
      String commodity, 
      String variety, 
      String state, 
      String district, 
      String market) {
    
    // Check if already exists
    if (favoriteCommodityRepository.existsByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
        user, commodity, variety, state, district, market)) {
      return false; // Already favorited
    }

    FavoriteCommodity favorite = new FavoriteCommodity(user, commodity, variety, state, district, market);
    favoriteCommodityRepository.save(favorite);
    return true;
  }

  /**
   * Remove a commodity from user's favorites
   */
  @Transactional
  public boolean removeFavorite(
      User user, 
      String commodity, 
      String variety, 
      String state, 
      String district, 
      String market) {
    
    Optional<FavoriteCommodity> favorite = 
        favoriteCommodityRepository.findByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
            user, commodity, variety, state, district, market);
    
    if (favorite.isPresent()) {
      favoriteCommodityRepository.delete(favorite.get());
      return true; // Successfully removed
    }
    
    return false; // Not found
  }

  /**
   * Toggle favorite status
   */
  @Transactional
  public boolean toggleFavorite(
      User user, 
      String commodity, 
      String variety, 
      String state, 
      String district, 
      String market) {
    
    Optional<FavoriteCommodity> existing = 
        favoriteCommodityRepository.findByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
            user, commodity, variety, state, district, market);
    
    if (existing.isPresent()) {
      // Remove if exists
      favoriteCommodityRepository.delete(existing.get());
      return false; // Removed
    } else {
      // Add if doesn't exist
      FavoriteCommodity favorite = new FavoriteCommodity(user, commodity, variety, state, district, market);
      favoriteCommodityRepository.save(favorite);
      return true; // Added
    }
  }

  /**
   * Check if commodity is favorited by user
   */
  public boolean isFavorite(
      User user, 
      String commodity, 
      String variety, 
      String state, 
      String district, 
      String market) {
    
    return favoriteCommodityRepository.existsByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
        user, commodity, variety, state, district, market);
  }

  /**
   * Get all favorites for a user
   */
  public List<FavoriteCommodity> getUserFavorites(User user) {
    return favoriteCommodityRepository.findByUserOrderByAddedDateDesc(user);
  }

  /**
   * Get count of user's favorites
   */
  public long getUserFavoritesCount(User user) {
    return favoriteCommodityRepository.countByUser(user);
  }
}