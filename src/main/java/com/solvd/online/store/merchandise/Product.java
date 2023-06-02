package com.solvd.online.store.merchandise;
import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    public Product(int productId, String productName, String productDescription, BigDecimal productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductId(int productId) {
    }

    public void setProductName(String productName) {
    }

    public void setProductDescription(String productDescription) {
    }

    public void setProductPrice(BigDecimal productPrice) {
    }
}
