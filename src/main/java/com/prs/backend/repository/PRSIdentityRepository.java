package com.prs.backend.repository;

import com.prs.backend.entity.PRSIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PRSIdentityRepository extends JpaRepository<PRSIdentity, Integer> {
    Optional<PRSIdentity> findByUserID(Integer userID);
}