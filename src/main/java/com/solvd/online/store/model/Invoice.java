package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;

public class Invoice {
    @JsonProperty
    private int invoiceId;
    @JsonProperty
    private int orderId;
    @JsonProperty
    private int userId;
    @JsonProperty
    private int paymentId;

    public Invoice() {
    }

    public Invoice(int invoiceId, int orderId, int userId, int paymentId) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentId = paymentId;
    }

    // Getters and setters

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
