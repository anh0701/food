package com.anh.foodsupplybe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MemberLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // (Ví dụ: "Silver", "Gold", "Platinum")
    private Double minSpent;
    private Integer minPurchases;
    private Double discountRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getMinPurchases() {
        return minPurchases;
    }

    public void setMinPurchases(Integer minPurchases) {
        this.minPurchases = minPurchases;
    }

    public Double getMinSpent() {
        return minSpent;
    }

    public void setMinSpent(Double minSpent) {
        this.minSpent = minSpent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

