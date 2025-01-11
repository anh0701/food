package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getDiscounts();
    Discount saveDiscount(Discount discount);
}
