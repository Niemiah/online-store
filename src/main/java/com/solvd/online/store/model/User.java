package com.solvd.online.store.model;
import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlElement
    private int userId;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String email;
    @XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    private List<Address> addresses;
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    private List<Order> orders;
    @XmlElementWrapper(name = "payments")
    @XmlElement(name = "payment")
    private List<Payment> payments;
    @XmlElementWrapper(name = "reviews")
    @XmlElement(name = "review")
    private List<Review> reviews;

    public User() {
        this.addresses = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public User(int userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addresses = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}