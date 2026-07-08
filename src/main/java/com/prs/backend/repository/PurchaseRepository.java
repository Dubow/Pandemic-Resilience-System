package com.prs.backend.repository;

import com.prs.backend.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findByUserID(Integer userID);

    List<Purchase> findByUserIDAndItemIDAndPurchaseDateAfter(
            Integer userID,
            Integer itemID,
            LocalDateTime date
    );
}