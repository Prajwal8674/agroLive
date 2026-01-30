package com.prajwal.agrolive.userEntity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "savedCommodities",
    uniqueConstraints = {
      @UniqueConstraint(
        columnNames = {
          "user_id",
          "commodity",
          "variety",
          "state",
          "district",
          "market"
        }
      )
    })
public class FavoriteCommodity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String commodity;

  @Column(nullable = false)
  private String variety;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String market;

  @Column(name = "added_date", nullable = false)
  private LocalDateTime addedDate;

  public FavoriteCommodity() {
    this.addedDate = LocalDateTime.now();
  }

  public FavoriteCommodity(
      User user,
      String commodity,
      String variety,
      String state,
      String district,
      String market) {

    this.user = user;
    this.commodity = commodity;
    this.variety = variety;
    this.state = state;
    this.district = district;
    this.market = market;
    this.addedDate = LocalDateTime.now();
  }

  // Getters & Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getCommodity() {
    return commodity;
  }

  public void setCommodity(String commodity) {
    this.commodity = commodity;
  }

  public String getVariety() {
    return variety;
  }

  public void setVariety(String variety) {
    this.variety = variety;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getMarket() {
    return market;
  }

  public void setMarket(String market) {
    this.market = market;
  }

  public LocalDateTime getAddedDate() {
    return addedDate;
  }

  public void setAddedDate(LocalDateTime addedDate) {
    this.addedDate = addedDate;
  }
}