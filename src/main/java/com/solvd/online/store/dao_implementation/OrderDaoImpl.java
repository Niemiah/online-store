package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.cart.Order;
import com.solvd.online.store.dao_interface.OrderDao;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    List<Order> orders;

    public OrderDaoImpl(){
        orders = new ArrayList<Order>();
        Order order1 = new Order(1,1);
        Order order2 = new Order(2,2);
        orders.add(order1);
        orders.add(order2);
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order.getOrderId());
        System.out.println("Order: Order Id " + order.getOrderId() + ", deleted from database");
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        orders.get(order.getOrderId()).setUserId(order.getUserId());
        System.out.println("Order: Order Id " + order.getOrderId() + ", updated in the database");
    }
}
