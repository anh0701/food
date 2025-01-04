package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.InvoiceItem;
import com.anh.foodsupplybe.repo.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceItemService {
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }
    public InvoiceItemService() {}
    public InvoiceItem save(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }
}
