package com.prs.backend.repository;

import com.prs.backend.entity.PurchaseRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PurchaseRuleRepository extends JpaRepository<PurchaseRule, Integer> {

    Optional<PurchaseRule> findByItemID(Integer itemID);

}