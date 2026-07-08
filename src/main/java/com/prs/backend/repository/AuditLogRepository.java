package com.prs.backend.repository;

import com.prs.backend.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog> findByUserID(Integer userID);
}