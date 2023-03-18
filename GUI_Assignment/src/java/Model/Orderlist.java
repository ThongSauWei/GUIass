package Model;

/**
 * @author LOH XIN JIE
 */
public class Orderlist {

    public Orders order;
    public Product product;
    public int ordersQuantity;
    public double ordersSubprice;

    public Orderlist(Orders order, Product product, int ordersQuantity, double ordersSubprice) {
        this.order = order;
        this.product = product;
        this.ordersQuantity = ordersQuantity;
        this.ordersSubprice = ordersSubprice;
    }

    public Orderlist(Orders order) {
        this.order = order;
    }

    public Orderlist() {
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
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrdersQuantity(int ordersQuantity) {
        this.ordersQuantity = ordersQuantity;
    }

    public void setOrdersSubprice(double ordersSubprice) {
        this.ordersSubprice = ordersSubprice;
    }

}
