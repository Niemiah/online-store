package com.solvd.online.store.service;
import com.solvd.online.store.model.OrderDetail;

public interface IOrderDetailService {
    void saveOrderDetailToDB(OrderDetail orderDetail);
    void updateOrderDetailInDB(OrderDetail orderDetail);
    OrderDetail getOrderDetailInDB(int id);
}