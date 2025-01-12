package com.anh.foodsupplybe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoice_id" )
    @JsonBackReference
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double quantity; // Số lượng sản phẩm
//    private double price; // Giá sản phẩm (đã áp dụng giảm giá)
//    private double discountedPrice; // Giá sau giảm giá (lưu cố định)
//    private double discountAmount; // Tổng giảm giá cho sản phẩm (nếu có)

    private double subtotal;

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

//    public double getDiscountedPrice() {
//        return discountedPrice;
//    }
//
//    public void setDiscountedPrice(double discountedPrice) {
//        this.discountedPrice = discountedPrice;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }

    public InvoiceItem(Long id, Invoice invoice, Product product, int quantity, double subtotal) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public InvoiceItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
