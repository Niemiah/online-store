package com.solvd.online.store.processing;

import java.sql.Date;

public class Payments {
    private int paymentId;
    private int userId;
    private String cardNumber;
    private Date expirationDate;

    public Payments(int paymentId, int userId, String cardNumber, Date expirationDate) {
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
    }

    public void setUserId(int userId) {
    }

    public void setCardNumber(String cardNumber) {
    }

    public void setExpirationDate(Date expirationDate) {
    }
}
