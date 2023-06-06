package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.cart.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    List<OrderDetail> orderDetails;

    public OrderDetailDaoImpl(){
        orderDetails = new ArrayList<OrderDetail>();
        OrderDetail orderDetail1 = new OrderDetail(1,1,1, 3);
        OrderDetail orderDetail2 = new OrderDetail(2,2,2, 3);
        orderDetails.add(orderDetail1);
        orderDetails.add(orderDetail2);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        System.out.println("OrderDetail: Order Id " + orderDetail.getOrderId() + ", Product Id " + orderDetail.getProductId() + " deleted from database");
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetails;
    }

    @Override
    public OrderDetail getOrderDetail(int orderId, int productId) {
        for(OrderDetail od : orderDetails){
            if(od.getOrderId() == orderId && od.getProductId() == productId){
                return od;
            }
        }
        return null;
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        // Implement update logic here
    }
}
