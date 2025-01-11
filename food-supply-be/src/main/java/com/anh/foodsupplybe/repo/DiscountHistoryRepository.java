package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.DiscountHistory;
import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountHistoryRepository extends JpaRepository<DiscountHistory, Long> {
    List<DiscountHistory> findByProduct(Product product);
    List<DiscountHistory> findByInvoice(Invoice invoice);
}