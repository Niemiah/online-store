package com.solvd.online.store.model;

public class JsonData {
    private Address address;
    private Payment payment;
    private Invoice invoice;
    private Inventory inventory;
    private Review review;

    // No-args constructor
    public JsonData() {
    }

    // Parameterized constructor
    public JsonData(Address address, Payment payment, Invoice invoice, Inventory inventory, Review review) {
        this.address = address;
        this.payment = payment;
        this.invoice = invoice;
        this.inventory = inventory;
        this.review = review;
    }

    // Getters
    public Address getAddress() {
        return address;
    }

    public Payment getPayment() {
        return payment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Review getReview() {
        return review;
    }

    // Setters
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}