package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDetail {
    @XmlElement
    private int orderId;
    @XmlElement
    private int productId;
    @XmlElement
    private int shippingId;
    @XmlElement
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, int shippingId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.shippingId = shippingId;
        this.quantity = quantity;
    }


    // Getters and setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }
}
