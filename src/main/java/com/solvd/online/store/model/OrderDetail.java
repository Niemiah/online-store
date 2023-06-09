package com.solvd.online.store.model;

public class OrderDetail {
    private int orderId;
    private int productId;
    private int shippingId;

    private int quantity;

    public OrderDetail(int orderId, int productId, int shippingId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.shippingId = shippingId;
        this.quantity = quantity;
    }

    public OrderDetail() {

    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getShippingId() {
        return shippingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
