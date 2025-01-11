package com.anh.foodsupplybe.service.impl;

import com.anh.foodsupplybe.dto.InvoiceRequest;
import com.anh.foodsupplybe.model.*;
import com.anh.foodsupplybe.repo.*;
import com.anh.foodsupplybe.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service(value = "invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountHistoryRepository discountHistoryRepository;

    public Invoice addInvoice(InvoiceRequest invoiceRequest) {
        double totalAmount = 0;
        log.error("check: " + totalAmount);
        Discount invoiceDiscount = null;
        if (invoiceRequest.getInvoiceDiscountId() != null) {
            invoiceDiscount = discountRepository.findById(invoiceRequest.getInvoiceDiscountId()).orElse(null);
        }

        Invoice invoice = new Invoice();
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setCustomerEmail(invoiceRequest.getCustomerEmail());

        invoice = invoiceRepository.save(invoice);

        for (InvoiceRequest.ItemRequest itemRequest : invoiceRequest.getItems()) {

            Product product = productRepository.findById(itemRequest.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            InvoiceItem item = new InvoiceItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setInvoice(invoice);

            Discount productDiscount = null;
            if (itemRequest.getDiscountId() != null) {
                productDiscount = discountRepository.findById(itemRequest.getDiscountId()).orElse(null);
                log.error("check: " + productDiscount);
            }

            double productPriceAfterDiscount = applyProductDiscount(product, productDiscount);
            item.setSubtotal(productPriceAfterDiscount * itemRequest.getQuantity());
            totalAmount += item.getSubtotal();

            if (productDiscount != null) {
                DiscountHistory productDiscountHistory = new DiscountHistory(
                        productDiscount,
                        productDiscount.getValue(),
                        productDiscount.getType(),
                        new Date(),
                        product,
                        invoice
                );
                discountHistoryRepository.save(productDiscountHistory);
            }

            invoiceItemRepository.save(item);
        }

        if (invoiceDiscount != null) {
            totalAmount = applyInvoiceDiscount(totalAmount, invoiceDiscount);

            DiscountHistory invoiceDiscountHistory = new DiscountHistory(
                    invoiceDiscount,
                    invoiceDiscount.getValue(),
                    invoiceDiscount.getType(),
                    new Date(),
                    null,
                    invoice
            );
            discountHistoryRepository.save(invoiceDiscountHistory);
        }

        invoice.setDate(new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        invoice.setTotalAmount(totalAmount);

        return invoiceRepository.save(invoice);
    }

    private double applyProductDiscount(Product product, Discount discount) {
        double price;
        if (discount == null) {
            return product.getPrice();
        }
        if (discount.getType() == Discount.DiscountType.PERCENTAGE) {
            price = product.getPrice() * (1 - discount.getValue() / 100);
        } else {
            price = product.getPrice() - discount.getValue();
        }
        if (price < 0) {
            price = 0;
        }
        return price;
    }

    private double applyInvoiceDiscount(double totalAmount, Discount discount) {
        if (discount == null) {
            return totalAmount;
        }
        double price;
        if (discount.getType() == Discount.DiscountType.PERCENTAGE) {
            price = totalAmount * (1 - discount.getValue() / 100);
        } else {
            price = totalAmount - discount.getValue();
        }
        if (price < 0) {
            price = 0;
        }
        return price;
    }


    public List<Invoice> getInvoices() {
        return invoiceRepository.findAllWithItems();
    }
}
