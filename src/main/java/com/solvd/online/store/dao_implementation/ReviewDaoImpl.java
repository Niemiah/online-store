package com.solvd.online.store.dao_implementation;
import com.solvd.online.store.feedback.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    List<Review> reviews;

    public ReviewDaoImpl(){
        reviews = new ArrayList<Review>();
        Review review1 = new Review(1, 1, 1, 5, "Would highly recommend!");
        Review review2 = new Review(2, 2, 2, 4, "Not the best, but it still works.");
        reviews.add(review1);
        reviews.add(review2);
    }

    @Override
    public void deleteReview(Review review) {
        reviews.remove(review.getReviewId());
        System.out.println("Review: Review Id " + review.getReviewId() + ", deleted from database");
    }

    @Override
    public List<Review> getAllReviews() {
        return reviews;
    }

    @Override
    public Review getReview(int reviewId) {
        return reviews.get(reviewId);
    }

    @Override
    public void updateReview(Review review) {
        reviews.get(review.getReviewId()).setReviewText(review.getReviewText());
        System.out.println("Review: Review Id " + review.getReviewId() + ", updated in the database");
    }
}
