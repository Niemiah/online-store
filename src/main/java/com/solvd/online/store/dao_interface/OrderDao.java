package com.solvd.online.store.dao_interface;
import com.solvd.online.store.cart.Order;
import java.util.List;

public interface OrderDao {
    public List<Order> getAllOrders();
    public Order getOrder(int orderId);
    public void updateOrder(Order order);
    public void deleteOrder(Order order);
}
