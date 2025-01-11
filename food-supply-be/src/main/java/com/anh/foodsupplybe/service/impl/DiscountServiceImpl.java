package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.model.Discount;
import com.anh.foodsupplybe.repo.DiscountRepository;
import com.anh.foodsupplybe.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "discountService")
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Override
    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
}