package Model;

import DataAccess.DBModel;

/**
 * @author LOH XIN JIE
 */
public class MemberAddress extends DBModel {

    private AddressBook address;
    private Member member;

    public MemberAddress(AddressBook address, Member member) {
        super("member_address");
        this.address = address != null ? address : new AddressBook();
        this.member = member != null ? member : new Member();
    }

    public MemberAddress(Member member) {
        super("member_address");
        this.address = new AddressBook();
        this.member = member != null ? member : new Member();
    }

    public MemberAddress(AddressBook address) {
        super("member_address");
        this.address = address != null ? address : new AddressBook();
        this.member = new Member();
    }

    public MemberAddress() {
        super("member_address");
        this.address = new AddressBook();
        this.member = new Member();
    }

    public AddressBook getAddress() {
        return address;
    }

    public Member getMember() {
        return member;
    }

    public void setAddress(AddressBook address) {
        this.address = address != null ? address : new AddressBook();
        this.member = new Member();
    }

    public void setMember(Member member) {
        this.address = new AddressBook();
        this.member = member != null ? member : new Member();
    }

}
