package com.solvd.online.store.cart;

import com.solvd.online.store.customers.User;

public class Order extends User {
    private int orderId;
    private int userId;

    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setOrderId(int orderId) {
    }

    public void setUserId(int userId) {
    }
}
