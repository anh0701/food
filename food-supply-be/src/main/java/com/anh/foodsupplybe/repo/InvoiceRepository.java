package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findInvoiceByDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT i FROM Invoice i LEFT JOIN FETCH i.items")
    List<Invoice> findAllWithItems();
}
