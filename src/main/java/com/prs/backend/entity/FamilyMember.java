package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "FamilyMembers")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FamilyMemberID")
    private Integer familyMemberID;

    @Column(name = "ParentUserID")
    private Integer parentUserID;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "PRSCode")
    private String prsCode;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Integer getFamilyMemberID() {
        return familyMemberID;
    }

    public void setFamilyMemberID(Integer familyMemberID) {
        this.familyMemberID = familyMemberID;
    }

    public Integer getParentUserID() {
        return parentUserID;
    }

    public void setParentUserID(Integer parentUserID) {
        this.parentUserID = parentUserID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPRSCode() {
        return prsCode;
    }

    public void setPRSCode(String prsCode) {
        this.prsCode = prsCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}