package Model;

/**
 * @author LOH XIN JIE
 */
import DataAccess.DBModel;
import java.util.*;

public class Discount extends DBModel {

    private int discountId;
    private int discountPercentage;
    private Date discountStartDate;
    private Date discountEndDate;
    private Product product;

    public Discount(int discountId, int discountPercentage, Date discountStartDate, Date discountEndDate, Product product) {
        super("discount");
        this.discountId = discountId;
        this.discountPercentage = discountPercentage;
        this.discountStartDate = discountStartDate != null ? discountStartDate : new Date(253402214400000L);
        this.discountEndDate = discountEndDate != null ? discountEndDate : new Date(253402214400000L);
        this.product = product != null ? product : new Product();
    }

    public Discount(int discountId) {
        super("discount");
        this.discountId = discountId;
    }

    public Discount() {
        super("discount");
        this.discountStartDate = new Date(253402214400000L);
        this.discountEndDate = new Date(253402214400000L);
        this.product = new Product();
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
        this.discountStartDate = discountStartDate != null ? discountStartDate : new Date(253402214400000L);
    }

    public void setDiscountEndDate(Date discountEndDate) {
        this.discountEndDate = discountEndDate != null ? discountEndDate : new Date(253402214400000L);
    }

    public void setProduct(Product product) {
        this.product = product  != null ? product : new Product();
    }

}
