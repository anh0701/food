package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.Discount;
import com.anh.foodsupplybe.repo.DiscountRepository;
import com.anh.foodsupplybe.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;
    @Autowired
    private DiscountRepository discountRepository;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADD_DISCOUNT')")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok(discountRepository.save(discount));
    }
    @GetMapping
    @PreAuthorize("hasAuthority('GET_DISCOUNT')")
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountRepository.findAll());
    }
}
