package com.prs.backend.repository;

import com.prs.backend.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Integer> {

    List<FamilyMember> findByParentUserID(Integer parentUserID);

}