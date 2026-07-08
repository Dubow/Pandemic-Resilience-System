package com.prs.backend.dto;

import java.time.LocalDate;

public class PRSIdentityRequest {

    private Integer userID;
    private String nationalID;
    private LocalDate dateOfBirth;

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getNationalID() { return nationalID; }
    public void setNationalID(String nationalID) { this.nationalID = nationalID; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}