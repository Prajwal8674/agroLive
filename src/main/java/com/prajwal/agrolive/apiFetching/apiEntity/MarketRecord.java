package com.prajwal.agrolive.apiFetching.apiEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "market_record", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"state", "district", "market", "commodity", "variety", "date"})
})
public class MarketRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private String district;
    private String market;
    private String commodity;
    private String variety;
    private String min_price;
    private String max_price;
    private String modal_price;

    @Column(name = "date")
    private String date;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getMarket() { return market; }
    public void setMarket(String market) { this.market = market; }

    public String getCommodity() { return commodity; }
    public void setCommodity(String commodity) { this.commodity = commodity; }

    public String getVariety() { return variety; }
    public void setVariety(String variety) { this.variety = variety; }

    public String getMin_price() { return min_price; }
    public void setMin_price(String min_price) { this.min_price = min_price; }

    public String getMax_price() { return max_price; }
    public void setMax_price(String max_price) { this.max_price = max_price; }

    public String getModal_price() { return modal_price; }
    public void setModal_price(String modal_price) { this.modal_price = modal_price; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
