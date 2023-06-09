package com.solvd.online.store.model;

import java.sql.Date;

public class Payment {
    private int paymentId;
    private int userId;
    private String cardNumber;
    private Date expirationDate;

    public Payment(int paymentId, int userId, String cardNumber, Date expirationDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
