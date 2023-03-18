package Model;

/**
 * @author LOH XIN JIE
 */
import java.util.*;

public class RateReview {

    public int reviewId;
    public String reviewText;
    public int reviewRating;
    public Date reviewDate;
    public Product product;
    public Member member;

    public RateReview(int reviewId, String reviewText, int reviewRating, Date reviewDate, Product product, Member member) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
        this.product = product;
        this.member = member;
    }

    public RateReview(int reviewId) {
        this.reviewId = reviewId;
    }

    public RateReview() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public Product getProduct() {
        return product;
    }

    public Member getMember() {
        return member;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
