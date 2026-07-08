package com.prs.backend.repository;

import com.prs.backend.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}