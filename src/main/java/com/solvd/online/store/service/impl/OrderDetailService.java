package com.solvd.online.store.service.impl;
import com.solvd.online.store.dao.IOrderDetailDAO;
import com.solvd.online.store.impl.OrderDetailDAO;
import com.solvd.online.store.model.OrderDetail;
import com.solvd.online.store.service.IOrderDetailService;


public class OrderDetailService implements IOrderDetailService {

    @Override
    public void saveOrderDetailToDB(OrderDetail orderDetail) {
        IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
        orderDetailDao.insert(orderDetail);
    }

    @Override
    public void updateOrderDetailInDB(OrderDetail orderDetail) {
        IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
        orderDetailDao.update(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailInDB(int id) {
        IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
        return orderDetailDao.getById(id);
    }
}
