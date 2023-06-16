package com.solvd.online.store.model;
import java.math.BigDecimal;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @XmlElement
    private int productId;
    @XmlElement
    private String productName;
    @XmlElement
    private String productDescription;
    @XmlElement
    private BigDecimal productPrice;
    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<Category> categories;
    @XmlElementWrapper(name = "orderDetails")
    @XmlElement(name = "orderDetail")
    private List<OrderDetail> orderDetails;
    @XmlElementWrapper(name = "reviews")
    @XmlElement(name = "review")
    private List<Review> reviews;

    public Product() {
        this.categories = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Product(int productId, String productName, String productDescription, BigDecimal productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.categories = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}