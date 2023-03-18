package Model;

/**
 * @author LOH XIN JIE
 */
public class Cartlist {

    public Cart cart;
    public Product product;
    public int cartQuantity;

    public Cartlist(Cart cart, Product product, int cartQuantity) {
        this.cart = cart;
        this.product = product;
        this.cartQuantity = cartQuantity;
    }

    public Cartlist(Cart cart) {
        this.cart = cart;
    }

    public Cartlist() {
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

}
