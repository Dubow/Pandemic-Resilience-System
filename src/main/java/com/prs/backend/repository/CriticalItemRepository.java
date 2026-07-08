package com.prs.backend.repository;

import com.prs.backend.entity.CriticalItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriticalItemRepository extends JpaRepository<CriticalItem, Integer> {
}