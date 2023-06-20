package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IOrderDAO;
import com.solvd.online.store.dao.impl.OrderDAO;
import com.solvd.online.store.service.IOrderService;
import com.solvd.online.store.model.Order;

public class OrderService implements IOrderService {

    @Override
    public void saveOrderToDB(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object is null.");
        }

        IOrderDAO orderDAO = new OrderDAO();
        orderDAO.insert(order);
    }

    @Override
    public void updateOrderInDB(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object is null.");
        }

        IOrderDAO orderDAO = new OrderDAO();
        orderDAO.update(order);
    }

    @Override
    public Order getOrderInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IOrderDAO orderDAO = new OrderDAO();
        return orderDAO.getById(id);
    }

    @Override
    public void deleteOrderFromDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IOrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteById(id);
    }
}