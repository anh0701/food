package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.dto.InvoiceRequest;
import com.anh.foodsupplybe.model.Discount;
import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.model.InvoiceItem;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.repo.InvoiceRepository;
import com.anh.foodsupplybe.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public interface InvoiceService {

    Invoice addInvoice(InvoiceRequest invoice);

    List<Invoice> getInvoices();

    private double applyProductDiscount(Product product, Discount discount) {
        return 0;
    }

    private double applyInvoiceDiscount(double totalAmount, Discount discount) {
        return 0;
    }
}