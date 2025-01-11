package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.DiscountHistory;

import java.util.List;

public interface DiscountHistoryService {
    List<DiscountHistory> getDiscountHistoryByInvoice(Long invoiceId);
    List<DiscountHistory> getDiscountHistoryByProduct(Long productId);
}
