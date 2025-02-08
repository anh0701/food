package com.anh.foodsupplybe.dto;

public class MemberLevelDto {
    private String name;  // (Ví dụ: "Silver", "Gold", "Platinum")
    private Double minSpent;
    private Integer minPurchases;
    private Double discountRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinSpent() {
        return minSpent;
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

    public void setMinSpent(Double minSpent) {
        this.minSpent = minSpent;
    }

    public Integer getMinPurchases() {
        return minPurchases;
    }

    public void setMinPurchases(Integer minPurchases) {
        this.minPurchases = minPurchases;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }
}
