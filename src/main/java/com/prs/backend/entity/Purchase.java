package com.prs.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PurchaseID")
    private Integer purchaseID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "StoreID")
    private Integer storeID;

    @Column(name = "ItemID")
    private Integer itemID;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "PurchaseDate", insertable = false, updatable = false)
    private LocalDateTime purchaseDate;

    public Integer getPurchaseID() { return purchaseID; }
    public void setPurchaseID(Integer purchaseID) { this.purchaseID = purchaseID; }

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public Integer getStoreID() { return storeID; }
    public void setStoreID(Integer storeID) { this.storeID = storeID; }

    public Integer getItemID() { return itemID; }
    public void setItemID(Integer itemID) { this.itemID = itemID; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getPurchaseDate() { return purchaseDate; }
}