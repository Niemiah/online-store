package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Order")
public class Order extends User {
    @XmlElement
    private int orderId;
    @XmlElement
    private int userId;

    public Order(int orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public Order() {
    }

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
}