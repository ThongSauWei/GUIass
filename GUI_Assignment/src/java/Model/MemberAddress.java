package Model;

/**
 * @author LOH XIN JIE
 */
public class MemberAddress {

    public AddressBook address;
    public Member member;

    public MemberAddress(AddressBook address, Member member) {
        this.address = address;
        this.member = member;
    }

    public MemberAddress(Member member) {
        this.member = member;
    }

    public MemberAddress(AddressBook address) {
        this.address = address;
    }

    public MemberAddress() {
    }

    public AddressBook getAddress() {
        return address;
    }

    public Member getMember() {
        return member;
    }

    public void setAddress(AddressBook address) {
        this.address = address;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
