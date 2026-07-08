package com.prs.backend.repository;

import com.prs.backend.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByStoreID(Integer storeID);
    Optional<Stock> findByStoreIDAndItemID(Integer storeID, Integer itemID);
}