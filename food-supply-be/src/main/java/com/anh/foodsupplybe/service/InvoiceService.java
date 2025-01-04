package com.anh.foodsupplybe.service;

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

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceItemService invoiceItemService;

    public Invoice addInvoice(Invoice invoice) {
        double totalAmount = 0;
        for (InvoiceItem item : invoice.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            item.setProduct(product);
            item.setSubtotal(product.getPrice() * item.getQuantity());
            totalAmount += item.getSubtotal();
            item.setInvoice(invoice);
//            invoiceItemService.save(item);

        }

        invoice.setDate(new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        invoice.setTotalAmount(totalAmount);
        return invoiceRepository.save(invoice);
    }
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAllWithItems();
    }
}
