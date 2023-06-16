package com.solvd.online.store.model;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement
    private int orderId;
    @XmlElement
    private int userId;
    @XmlElementWrapper(name = "orderDetails")
    @XmlElement(name = "orderDetail")
    private List<OrderDetail> orderDetails;

    public Order() {
        this.orderDetails = new ArrayList<>();
    }

    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDetails = new ArrayList<>();
    }

    // Getters and setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}