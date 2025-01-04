package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/add")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.addInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }
    @GetMapping
    public ResponseEntity<List<Invoice>> getInvoices() {
        List<Invoice> invoices = invoiceService.getInvoices();
        return ResponseEntity.ok(invoices);
    }
}
