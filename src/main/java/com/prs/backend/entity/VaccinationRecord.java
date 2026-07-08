package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "VaccinationRecords")
public class VaccinationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VaccinationID")
    private Integer vaccinationID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "VaccineName")
    private String vaccineName;

    @Column(name = "DoseNumber")
    private Integer doseNumber;

    @Column(name = "VaccinationDate")
    private LocalDate vaccinationDate;

    @Column(name = "VerificationStatus")
    private String verificationStatus = "PENDING";

    @Column(name = "FHIRJson")
    private String fhirJson;

    @Column(name = "UploadedAt", insertable = false, updatable = false)
    private LocalDateTime uploadedAt;

    public Integer getVaccinationID() { return vaccinationID; }
    public void setVaccinationID(Integer vaccinationID) { this.vaccinationID = vaccinationID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getVaccineName() { return vaccineName; }
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }

    public Integer getDoseNumber() { return doseNumber; }
    public void setDoseNumber(Integer doseNumber) { this.doseNumber = doseNumber; }

    public LocalDate getVaccinationDate() { return vaccinationDate; }
    public void setVaccinationDate(LocalDate vaccinationDate) { this.vaccinationDate = vaccinationDate; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }

    public String getFhirJson() { return fhirJson; }
    public void setFhirJson(String fhirJson) { this.fhirJson = fhirJson; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
}