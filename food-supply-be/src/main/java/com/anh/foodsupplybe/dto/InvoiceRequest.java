package com.anh.foodsupplybe.dto;

import com.anh.foodsupplybe.model.Product;

import java.util.List;

public class InvoiceRequest {
    private String customerEmail;
    private String customerName;
    private List <ItemRequest> items;
    private Long invoiceDiscountId;

    public static class ItemRequest {
        private Product product;
        private double quantity;
        private Long discountId;

        public Long getDiscountId() {
            return discountId;
        }

        public void setDiscountId(Long discountId) {
            this.discountId = discountId;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public Long getInvoiceDiscountId() {
        return invoiceDiscountId;
    }

    public void setInvoiceDiscountId(Long invoiceDiscountId) {
        this.invoiceDiscountId = invoiceDiscountId;
    }
}

