package com.solvd.online.store.dao_interface;
import com.solvd.online.store.feedback.Review;
import java.util.List;

public interface ReviewDao {
    public List<Review> getAllReviews();
    public Review getReview(int reviewId);
    public void updateReview(Review review);
    public void deleteReview(Review review);
}
