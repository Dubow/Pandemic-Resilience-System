package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRS_Identities")
public class PRSIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRSID")
    private Integer prsID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "PRSCode")
    private String prsCode;

    @Column(name = "NationalIDEncrypted")
    private byte[] nationalIDEncrypted;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Integer getPrsID() { return prsID; }
    public void setPrsID(Integer prsID) { this.prsID = prsID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getPrsCode() { return prsCode; }
    public void setPrsCode(String prsCode) { this.prsCode = prsCode; }

    public byte[] getNationalIDEncrypted() { return nationalIDEncrypted; }
    public void setNationalIDEncrypted(byte[] nationalIDEncrypted) { this.nationalIDEncrypted = nationalIDEncrypted; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}