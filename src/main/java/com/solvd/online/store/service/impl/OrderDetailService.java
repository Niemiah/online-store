package com.solvd.online.store.service.impl;

import com.solvd.online.store.dao.IOrderDetailDAO;
import com.solvd.online.store.dao.impl.OrderDetailDAO;
import com.solvd.online.store.service.IOrderDetailService;
import com.solvd.online.store.model.OrderDetail;

public class OrderDetailService implements IOrderDetailService {

    @Override
    public void saveOrderDetailToDB(OrderDetail orderDetail) {
        if (orderDetail == null) {
            throw new IllegalArgumentException("OrderDetail object is null.");
        }

        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.insert(orderDetail);
    }

    @Override
    public void updateOrderDetailInDB(OrderDetail orderDetail) {
        if (orderDetail == null) {
            throw new IllegalArgumentException("OrderDetail object is null.");
        }

        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.update(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailInDB(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }

        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getById(id);
    }
}