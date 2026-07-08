package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Merchants")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MerchantID")
    private Integer merchantID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "BusinessName")
    private String businessName;

    @Column(name = "BusinessLicenseNumber")
    private String businessLicenseNumber;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Integer getMerchantID() { return merchantID; }
    public void setMerchantID(Integer merchantID) { this.merchantID = merchantID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessLicenseNumber() { return businessLicenseNumber; }
    public void setBusinessLicenseNumber(String businessLicenseNumber) { this.businessLicenseNumber = businessLicenseNumber; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}