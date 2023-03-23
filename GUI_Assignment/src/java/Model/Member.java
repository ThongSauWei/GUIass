package Model;

import DataAccess.DBModel;

/**
 * @author LOH XIN JIE
 */
public class Member extends DBModel {

    private int memberId;
    private String memberName;
    private String memberPass;

    public Member(int memberId, String memberName, String memberPass) {
        super("member");
        this.memberId = memberId;
        this.memberName = memberName != null ? memberName : "";
        this.memberPass = memberPass != null ? memberPass : "";
    }

    public Member(int memberId) {
        super("member");
        this.memberId = memberId;
    }

    public Member() {
        super("member");
        this.memberName = "";
        this.memberPass = "";
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPass() {
        return memberPass;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName != null ? memberName : "";
    }

    public void setMemberPass(String memberPass) {
        this.memberPass = memberPass != null ? memberPass : "";
    }
}
