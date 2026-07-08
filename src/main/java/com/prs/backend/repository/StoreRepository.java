package com.prs.backend.repository;

import com.prs.backend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByMerchantID(Integer merchantID);
}