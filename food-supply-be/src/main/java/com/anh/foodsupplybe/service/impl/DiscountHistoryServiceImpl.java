package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.model.DiscountHistory;
import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.repo.DiscountHistoryRepository;
import com.anh.foodsupplybe.repo.InvoiceRepository;
import com.anh.foodsupplybe.repo.ProductRepository;
import com.anh.foodsupplybe.service.DiscountHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "discountHistoryService")
public class DiscountHistoryServiceImpl implements DiscountHistoryService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DiscountHistoryRepository discountHistoryRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<DiscountHistory> getDiscountHistoryByInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return discountHistoryRepository.findByInvoice(invoice);
    }

    @Override
    public List<DiscountHistory> getDiscountHistoryByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return discountHistoryRepository.findByProduct(product);
    }
}
