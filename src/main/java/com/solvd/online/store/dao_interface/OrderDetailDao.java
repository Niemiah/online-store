package com.solvd.online.store.dao_interface;
import com.solvd.online.store.cart.OrderDetail;
import java.util.List;

public interface OrderDetailDao {
    public List<OrderDetail> getAllOrderDetails();
    public OrderDetail getOrderDetail(int orderId, int productId);
    public void updateOrderDetail(OrderDetail orderDetail);
    public void deleteOrderDetail(OrderDetail orderDetail);
}