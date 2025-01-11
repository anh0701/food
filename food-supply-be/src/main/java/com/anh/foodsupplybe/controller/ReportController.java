package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.repo.InvoiceRepository;
import com.anh.foodsupplybe.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private RevenueService revenueService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/revenue-by-month")
    @PreAuthorize("hasAuthority('GET_REPORT')")

    public ResponseEntity<Map<YearMonth, Double>> getRevenueByMonth(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<Invoice> orders = invoiceRepository.findInvoiceByDateBetween(startDate, endDate);
        Map<YearMonth, Double> revenue = revenueService.getRevenueByMonth(orders);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/revenue-by-year")
    @PreAuthorize("hasAuthority('GET_REPORT')")

    public ResponseEntity<Map<Integer, Double>> getRevenueByYear(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<Invoice> orders = invoiceRepository.findInvoiceByDateBetween(startDate, endDate);
        Map<Integer, Double> revenue = revenueService.getRevenueByYear(orders);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/revenue-by-quarter")
    @PreAuthorize("hasAuthority('GET_REPORT')")

    public ResponseEntity<Map<Integer, Double>> getRevenueByQuarter(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<Invoice> orders = invoiceRepository.findInvoiceByDateBetween(startDate, endDate);
        Map<Integer, Double> revenue = revenueService.getRevenueByQuarter(orders);
        return ResponseEntity.ok(revenue);
    }
}
