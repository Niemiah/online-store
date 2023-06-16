package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IOrderDAO;
import com.solvd.online.store.dao.impl.OrderDAO;
import com.solvd.online.store.service.IOrderService;
import com.solvd.online.store.model.Order;

public class OrderService implements IOrderService {

    @Override
    public void saveOrderToDB(Order order) {
        IOrderDAO orderDAO = new OrderDAO();
        orderDAO.insert(order);
    }

    @Override
    public void updateOrderInDB(Order order) {
        IOrderDAO orderDAO = new OrderDAO();
        orderDAO.update(order);
    }

    @Override
    public Order getOrderInDB(int id) {
        IOrderDAO orderDAO = new OrderDAO();
        return orderDAO.getById(id);
    }

    @Override
    public void deleteOrderFromDB(int id) {

    }

    @Override
    public String getAllOrdersFromDB() {
        return null;
    }
}