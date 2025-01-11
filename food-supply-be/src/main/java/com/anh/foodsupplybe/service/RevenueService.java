package com.anh.foodsupplybe.service;

import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.repo.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RevenueService {


    Map<Integer, Double> getRevenueByYear(List<Invoice> orders);

    Map<Integer, Double> getRevenueByQuarter(List<Invoice> orders);

    Map<YearMonth, Double> getRevenueByMonth(List<Invoice> orders);

}