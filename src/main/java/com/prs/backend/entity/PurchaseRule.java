package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PurchaseRules")
public class PurchaseRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RuleID")
    private Integer ruleID;

    @Column(name = "ItemID")
    private Integer itemID;

    @Column(name = "MaxQuantity")
    private Integer maxQuantity;

    @Column(name = "PeriodType")
    private String periodType;

    @Column(name = "AllowedDOBLastDigits")
    private String allowedDOBLastDigits;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Integer getRuleID() { return ruleID; }
    public void setRuleID(Integer ruleID) { this.ruleID = ruleID; }

    public Integer getItemID() { return itemID; }
    public void setItemID(Integer itemID) { this.itemID = itemID; }

    public Integer getMaxQuantity() { return maxQuantity; }
    public void setMaxQuantity(Integer maxQuantity) { this.maxQuantity = maxQuantity; }

    public String getPeriodType() { return periodType; }
    public void setPeriodType(String periodType) { this.periodType = periodType; }

    public String getAllowedDOBLastDigits() { return allowedDOBLastDigits; }
    public void setAllowedDOBLastDigits(String allowedDOBLastDigits) { this.allowedDOBLastDigits = allowedDOBLastDigits; }

    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}