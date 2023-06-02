package com.solvd.online.store.feedback;

public class Review {
    private int reviewId;
    private int productId;
    private int userId;
    private int rating;
    private String reviewText;

    public Review(int reviewId, int productId, int userId, int rating, String reviewText) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewId(int reviewId) {
    }

    public void setProductId(int productId) {
    }

    public void setUserId(int userId) {
    }

    public void setRating(int rating) {
    }

    public void setReviewText(String reviewText) {
    }
}
