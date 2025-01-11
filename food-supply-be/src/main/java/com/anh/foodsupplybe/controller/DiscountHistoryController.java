package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.DiscountHistory;
import com.anh.foodsupplybe.service.DiscountHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discount-history")
public class DiscountHistoryController {

    @Autowired
    private DiscountHistoryService discountHistoryService;

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAuthority('GET_HISTORY_DISCOUNT')")
    public ResponseEntity<List<DiscountHistory>> getDiscountHistoryByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(discountHistoryService.getDiscountHistoryByProduct(productId));
    }

    @GetMapping("/invoice/{invoiceId}")
    @PreAuthorize("hasAuthority('GET_HISTORY_DISCOUNT')")
    public ResponseEntity<List<DiscountHistory>> getDiscountHistoryByInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(discountHistoryService.getDiscountHistoryByInvoice(invoiceId));
    }
}


