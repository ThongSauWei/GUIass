package Model;

/**
 * @author LOH XIN JIE
 */
import java.util.*;

public class Discount {

    public int discountId;
    public int discountPercentage;
    public Date discountStartDate;
    public Date discountEndDate;
    public Product product;

    public Discount(int discountId, int discountPercentage, Date discountStartDate, Date discountEndDate, Product product) {
        this.discountId = discountId;
        this.discountPercentage = discountPercentage;
        this.discountStartDate = discountStartDate;
        this.discountEndDate = discountEndDate;
        this.product = product;
    }

    public Discount(int discountId) {
        this.discountId = discountId;
    }

    public Discount() {
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public Date getDiscountStartDate() {
        return discountStartDate;
    }

    public Date getDiscountEndDate() {
        return discountEndDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDiscountStartDate(Date discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    public void setDiscountEndDate(Date discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
