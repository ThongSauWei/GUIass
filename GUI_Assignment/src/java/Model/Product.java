package Model;

import DataAccess.DBModel;

/**
 * @author LOH XIN JIE
 */
public class Product extends DBModel {

    private int productId;
    private String productName;
    private String productDesc;
    private double productPrice;
    private char productActive;

    public Product(int productId, String productName, String productDesc, double productPrice, char productActive) {
        super("product");
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productActive = productActive;
    }

    public Product(int productId) {
        super("product");
        this.productId = productId;
    }

    public Product() {
        super("product");
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public char getProductActive() {
        return productActive;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductActive(char productActive) {
        this.productActive = productActive;
    }

}
