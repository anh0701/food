package com.anh.foodsupplybe.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DiscountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    private double discountValue;
    private Discount.DiscountType discountType;
    private Date dateApplied;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public DiscountHistory(Discount discount, double discountValue, Discount.DiscountType discountType, Date dateApplied, Product product, Invoice invoice) {
        this.discount = discount;
        this.discountValue = discountValue;
        this.discountType = discountType;
        this.dateApplied = dateApplied;
        this.product = product;
        this.invoice = invoice;
    }

    public DiscountHistory(Long id, Discount discount, double discountValue, Discount.DiscountType discountType, Date dateApplied, Product product, Invoice invoice) {
        this.id = id;
        this.discount = discount;
        this.discountValue = discountValue;
        this.discountType = discountType;
        this.dateApplied = dateApplied;
        this.product = product;
        this.invoice = invoice;
    }

    public DiscountHistory() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Discount.DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Discount.DiscountType discountType) {
        this.discountType = discountType;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}

