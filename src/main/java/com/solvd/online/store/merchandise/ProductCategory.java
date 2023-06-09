package com.solvd.online.store.merchandise;

public class ProductCategory {
    private int productId;
    private int categoryId;

    public ProductCategory(int productId, int categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public ProductCategory() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
