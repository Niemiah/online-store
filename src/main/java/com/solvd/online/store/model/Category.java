package com.solvd.online.store.model;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    @XmlElement
    private int categoryId;
    @XmlElement
    private String categoryName;
    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products;

    public Category() {
        this.products = new ArrayList<>();
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
    }

    // Getters and setters

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}