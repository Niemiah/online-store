package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;

public class Inventory {
    @JsonProperty
    private int inventoryId;
    @JsonProperty
    private int productId;
    @JsonProperty
    private int stock;

    public Inventory() {
    }

    public Inventory(int inventoryId, int productId, int stock) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.stock = stock;
    }

    // Getters and setters

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
