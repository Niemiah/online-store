package com.solvd.online.store.model;
import java.util.List;


public class Orders {

    private List<Order> orders;

    public Orders() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}