package com.solvd.online.store.model;

public class Invoice {
    private int invoiceId;
    private int orderId;
    private int userId;
    private int paymentId;

    public Invoice(int invoiceId, int orderId, int userId, int paymentId) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentId = paymentId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
