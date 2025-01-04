package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
