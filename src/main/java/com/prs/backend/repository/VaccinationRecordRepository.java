package com.prs.backend.repository;

import com.prs.backend.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Integer> {
    List<VaccinationRecord> findByUserID(Integer userID);
}