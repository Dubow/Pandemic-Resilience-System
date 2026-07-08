package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLogs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuditID")
    private Integer auditID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "ActionType")
    private String actionType;

    @Column(name = "TableName")
    private String tableName;

    @Column(name = "RecordID")
    private Integer recordID;

    @Column(name = "ActionTime", insertable = false, updatable = false)
    private LocalDateTime actionTime;

    public Integer getAuditID() { return auditID; }
    public void setAuditID(Integer auditID) { this.auditID = auditID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }

    public Integer getRecordID() { return recordID; }
    public void setRecordID(Integer recordID) { this.recordID = recordID; }

    public LocalDateTime getActionTime() { return actionTime; }
}