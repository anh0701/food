package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.InvoiceItem;
import com.anh.foodsupplybe.repo.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface InvoiceItemService {
    InvoiceItem save(InvoiceItem invoiceItem);
}
