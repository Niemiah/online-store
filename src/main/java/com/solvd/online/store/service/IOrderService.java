package com.solvd.online.store.service;
import com.solvd.online.store.model.Order;

public interface IOrderService {
    void saveOrderToDB(Order order);

    void updateOrderInDB(Order order);

    Order getOrderInDB(int id);

    void deleteOrderFromDB(int id);
}