package com.prajwal.agrolive.apiFetching.apiRepository;

import com.prajwal.agrolive.apiFetching.apiEntity.MarketRecord;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Transactional
    void deleteByDateBefore(String date);

    // ✅ NEW: filter by commodity + state
    Page<MarketRecord> findByCommodityIgnoreCaseAndStateIgnoreCase(
            String commodity,
            String state,
            PageRequest pageRequest
    );
}
