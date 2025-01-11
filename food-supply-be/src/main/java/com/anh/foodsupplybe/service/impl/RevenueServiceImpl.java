package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.model.Invoice;
import com.anh.foodsupplybe.service.RevenueService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "revenueService")
public class RevenueServiceImpl implements RevenueService {

    public Map<Integer, Double> getRevenueByYear(List<Invoice> orders) {
        Map<Integer, Double> revenueByYear = new HashMap<>();

        for (Invoice order : orders) {
            int year = order.getDate().getYear();
            revenueByYear.put(year, revenueByYear.getOrDefault(year, 0.0) + order.getTotalAmount());
        }

        return revenueByYear;
    }

    public Map<Integer, Double> getRevenueByQuarter(List<Invoice> orders) {
        Map<Integer, Double> revenueByQuarter = new HashMap<>();

        for (Invoice order : orders) {
            int quarter = (order.getDate().getMonthValue() - 1) / 3 + 1;
            revenueByQuarter.put(quarter, revenueByQuarter.getOrDefault(quarter, 0.0) + order.getTotalAmount());
        }

        return revenueByQuarter;
    }

    public Map<YearMonth, Double> getRevenueByMonth(List<Invoice> orders) {
        Map<YearMonth, Double> revenueByMonth = new HashMap<>();

        for (Invoice order : orders) {
            YearMonth yearMonth = YearMonth.from(order.getDate());
            revenueByMonth.put(yearMonth, revenueByMonth.getOrDefault(yearMonth, 0.0) + order.getTotalAmount());
        }

        return revenueByMonth;
    }
}

