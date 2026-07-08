package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StoreID")
    private Integer storeID;

    @Column(name = "MerchantID")
    private Integer merchantID;

    @Column(name = "StoreName")
    private String storeName;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Integer getStoreID() { return storeID; }
    public void setStoreID(Integer storeID) { this.storeID = storeID; }

    public Integer getMerchantID() { return merchantID; }
    public void setMerchantID(Integer merchantID) { this.merchantID = merchantID; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}