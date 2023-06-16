package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;
import java.sql.Date;

public class Payment {
    @JsonProperty
    private int paymentId;
    @JsonProperty
    private int userId;
    @JsonProperty
    private String cardNumber;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expirationDate;

    public Payment() {
    }

    public Payment(int paymentId, int userId, String cardNumber, Date expirationDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    // Getters and setters

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

