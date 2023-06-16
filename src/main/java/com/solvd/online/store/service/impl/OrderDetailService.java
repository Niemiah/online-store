package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IOrderDetailDAO;
import com.solvd.online.store.dao.impl.OrderDetailDAO;
import com.solvd.online.store.model.OrderDetail;
import com.solvd.online.store.service.IOrderDetailService;


public class OrderDetailService implements IOrderDetailService {

    @Override
    public void saveOrderDetailToDB(OrderDetail orderDetail) {
        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.insert(orderDetail);
    }

    @Override
    public void updateOrderDetailInDB(OrderDetail orderDetail) {
        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.update(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailInDB(int id) {
        IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        return orderDetailDAO.getById(id);
    }
}
