package Model;

/**
 * @author LENOVO
 */
import DataAccess.DBModel;
import java.util.*;

public class Orders extends DBModel {

    private int ordersId;
    private Date ordersDate;
    private String ordersPaymentType;
    private double ordersTtlPrice;
    private double ordersTax;
    private double ordersDeliveryFee;
    private double ordersExpressShipping;
    private Member member;
    private AddressBook address;

    public Orders(int ordersId, Date ordersDate, String ordersPaymentType, double ordersTtlPrice, double ordersTax, double ordersDeliveryFee, double ordersExpressShipping, Member member, AddressBook address) {
        super("orders");
        this.ordersId = ordersId;
        this.ordersDate = ordersDate != null ? ordersDate : new Date(253402214400000L);
        this.ordersPaymentType = ordersPaymentType != null ? ordersPaymentType : "";
        this.ordersTtlPrice = ordersTtlPrice;
        this.ordersTax = ordersTax;
        this.ordersDeliveryFee = ordersDeliveryFee;
        this.ordersExpressShipping = ordersExpressShipping;
        this.member = member != null ? member : new Member();
        this.address = address != null ? address : new AddressBook();
    }

    public Orders(int ordersId) {
        super("orders");
        this.ordersId = ordersId;
    }

    public Orders() {
        super("orders");
    }

    public int getOrdersId() {
        return ordersId;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public String getOrdersPaymentType() {
        return ordersPaymentType;
    }

    public double getOrdersTtlPrice() {
        return ordersTtlPrice;
    }

    public double getOrdersTax() {
        return ordersTax;
    }

    public double getOrdersDeliveryFee() {
        return ordersDeliveryFee;
    }

    public double getOrdersExpressShipping() {
        return ordersExpressShipping;
    }

    public Member getMember() {
        return member;
    }

    public AddressBook getAddress() {
        return address;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate != null ? ordersDate : new Date(253402214400000L);
    }

    public void setOrdersPaymentType(String ordersPaymentType) {
        this.ordersPaymentType = ordersPaymentType != null ? ordersPaymentType : "";
    }

    public void setOrdersTtlPrice(double ordersTtlPrice) {
        this.ordersTtlPrice = ordersTtlPrice;
    }

    public void setOrdersTax(double ordersTax) {
        this.ordersTax = ordersTax;
    }

    public void setOrdersDeliveryFee(double ordersDeliveryFee) {
        this.ordersDeliveryFee = ordersDeliveryFee;
    }

    public void setOrdersExpressShipping(double ordersExpressShipping) {
        this.ordersExpressShipping = ordersExpressShipping;
    }

    public void setMember(Member member) {
        this.member = member != null ? member : new Member();
    }

    public void setAddress(AddressBook address) {
        this.address = address != null ? address : new AddressBook();
    }

}
