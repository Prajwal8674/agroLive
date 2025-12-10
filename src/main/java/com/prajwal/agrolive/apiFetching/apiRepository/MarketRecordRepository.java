package com.prajwal.agrolive.apiFetching.apiRepository;

import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRecordRepository extends JpaRepository<MarketRecord, Long> {

    boolean existsByStateAndDistrictAndMarketAndCommodityAndVarietyAndDate(
            String state,
            String district,
            String market,
            String commodity,
            String variety,
            String date
    );
}
