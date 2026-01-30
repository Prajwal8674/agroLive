package com.prajwal.agrolive.userRepository;

import com.prajwal.agrolive.userEntity.FavoriteCommodity;
import com.prajwal.agrolive.userEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteCommodityRepository extends JpaRepository<FavoriteCommodity, Long> {

  List<FavoriteCommodity> findByUserOrderByAddedDateDesc(User user);

  Optional<FavoriteCommodity> findByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
      User user,
      String commodity,
      String variety,
      String state,
      String district,
      String market);

  boolean existsByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
      User user,
      String commodity,
      String variety,
      String state,
      String district,
      String market);

  // Add this method
  void deleteByUserAndCommodityAndVarietyAndStateAndDistrictAndMarket(
      User user,
      String commodity,
      String variety,
      String state,
      String district,
      String market);

  long countByUser(User user);
}