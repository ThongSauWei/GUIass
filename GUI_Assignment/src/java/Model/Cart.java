package Model;

/**
 * @author LOH XIN JIE
 */
public class Cart {

    public int cartId;
    public Member member;

    public Cart(int cartId, Member member) {
        this.cartId = cartId;
        this.member = member;
    }

    public Cart(int cartId) {
        this.cartId = cartId;
    }

    public Cart() {
    }

    public int getCartId() {
        return cartId;
    }

    public Member getMember() {
        return member;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
