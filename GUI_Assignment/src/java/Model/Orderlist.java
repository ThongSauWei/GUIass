package Model;

import DataAccess.DBModel;

/**
 * @author LOH XIN JIE
 */
public class Orderlist extends DBModel {

    private Orders order;
    private Product product;
    private int ordersQuantity;
    private double ordersSubprice;

    public Orderlist(Orders order, Product product, int ordersQuantity, double ordersSubprice) {
        super("orderlist");
        this.order = order != null ? order : new Orders();
        this.product = product != null ? product : new Product();
        this.ordersQuantity = ordersQuantity;
        this.ordersSubprice = ordersSubprice;
    }

    public Orderlist(Orders order) {
        super("orderlist");
        this.order = order;
        this.product = new Product();
    }

    public Orderlist() {
        super("orderlist");
        this.order = new Orders();
        this.product = new Product();
    }

    public Orders getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getOrdersQuantity() {
        return ordersQuantity;
    }

    public double getOrdersSubprice() {
        return ordersSubprice;
    }

    public void setOrder(Orders order) {
        this.order = order != null ? order : new Orders();
    }

    public void setProduct(Product product) {
        this.product = product != null ? product : new Product();
    }

    public void setOrdersQuantity(int ordersQuantity) {
        this.ordersQuantity = ordersQuantity;
    }

    public void setOrdersSubprice(double ordersSubprice) {
        this.ordersSubprice = ordersSubprice;
    }

}
