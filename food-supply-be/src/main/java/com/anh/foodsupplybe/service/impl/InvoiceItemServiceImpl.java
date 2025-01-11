package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.model.InvoiceItem;
import com.anh.foodsupplybe.repo.InvoiceItemRepository;
import com.anh.foodsupplybe.service.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "invoiceItemService")
public class InvoiceItemServiceImpl implements InvoiceItemService {
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    public InvoiceItem save(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }
}