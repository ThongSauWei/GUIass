package DataAccess;

import Model.*;

/**
 * @author LOH XIN JIE
 */
public class DBTable {

    public DbSet<Staff> Staff;
    public DbSet<AddressBook> AddressBook;
    public DbSet<Member> Member;
    public DbSet<MemberAddress> MemberAddress;
    public DbSet<Orders> Orders;
    public DbSet<Product> Product;
    public DbSet<Orderlist> Orderlist;
    public DbSet<Cart> Cart;
    public DbSet<Cartlist> Cartlist;
    public DbSet<Discount> Discount;
    public DbSet<RateReview> RateReview;

    public DBTable() {
        this.Staff = new DbSet<>();
        this.AddressBook = new DbSet<>();
        this.Member = new DbSet<>();
        this.MemberAddress = new DbSet<>();
        this.Orders = new DbSet<>();
        this.Product = new DbSet<>();
        this.Orderlist = new DbSet<>();
        this.Cart = new DbSet<>();
        this.Cartlist = new DbSet<>();
        this.Discount = new DbSet<>();
        this.RateReview = new DbSet<>();
    }

    public DbSet<Staff> getStaff() {
        return Staff;
    }

    public DbSet<AddressBook> getAddressBook() {
        return AddressBook;
    }

    public DbSet<Member> getMember() {
        return Member;
    }

    public DbSet<MemberAddress> getMemberAddress() {
        return MemberAddress;
    }

    public DbSet<Orders> getOrders() {
        return Orders;
    }

    public DbSet<Product> getProduct() {
        return Product;
    }

    public DbSet<Orderlist> getOrderlist() {
        return Orderlist;
    }

    public DbSet<Cart> getCart() {
        return Cart;
    }

    public DbSet<Cartlist> getCartlist() {
        return Cartlist;
    }

    public DbSet<Discount> getDiscount() {
        return Discount;
    }

    public DbSet<RateReview> getRateReview() {
        return RateReview;
    }
}
