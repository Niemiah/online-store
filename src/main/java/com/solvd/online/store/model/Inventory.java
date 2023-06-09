package com.solvd.online.store.model;

public class Inventory {
    private int inventoryId;
    private int categoryId;
    private int stock;
    private int productId;

    public Inventory(int inventoryId, int categoryId, int stock, int productId) {
        this.inventoryId = inventoryId;
        this.categoryId = categoryId;
        this.stock = stock;
        this.productId = productId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
