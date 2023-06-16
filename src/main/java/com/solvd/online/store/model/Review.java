package com.solvd.online.store.model;
import com.fasterxml.jackson.annotation.*;

public class Review {
    @JsonProperty
    private int reviewId;
    @JsonProperty
    private int productId;
    @JsonProperty
    private int userId;
    @JsonProperty
    private int rating;
    @JsonProperty
    private String reviewText;

    public Review() {
    }

    public Review(int reviewId, int productId, int userId, int rating, String reviewText) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    // Getters and setters

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}