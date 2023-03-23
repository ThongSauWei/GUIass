package Model;

import DataAccess.DBModel;

/**
 * @author LOH XIN JIE
 */
public class AddressBook extends DBModel {

    private int addressId;
    private String addressName;
    private String addressPhone;
    private String addressNo;
    private String addressStreet;
    private String addressState;
    private String addressCity;
    private String addressPostcode;

    public AddressBook(int addressId, String addressName, String addressPhone, String addressNo, String addressStreet, String addressState, String addressCity, String addressPostcode) {
        super("addressbook");
        this.addressId = addressId;
        this.addressName = addressName != null ? addressName : "";
        this.addressPhone = addressPhone != null ? addressPhone : "";
        this.addressNo = addressNo != null ? addressNo : "";
        this.addressStreet = addressStreet != null ? addressStreet : "";
        this.addressState = addressState != null ? addressState : "";
        this.addressCity = addressCity != null ? addressCity : "";
        this.addressPostcode = addressPostcode != null ? addressPostcode : "";
    }

    public AddressBook(int addressId) {
        super("addressbook");
        this.addressId = addressId;
    }

    public AddressBook() {
        super("addressbook");
        this.addressName = "";
        this.addressPhone = "";
        this.addressNo = "";
        this.addressStreet = "";
        this.addressState = "";
        this.addressCity = "";
        this.addressPostcode = "";

    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressState() {
        return addressState;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName != null ? addressName : "";
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone != null ? addressPhone : "";
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo != null ? addressNo : "";
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet != null ? addressStreet : "";
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState != null ? addressState : "";
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity != null ? addressCity : "";
    }

    public void setAddressPostcode(String addressPostcode) {
        this.addressPostcode = addressPostcode != null ? addressPostcode : "";
    }
}
