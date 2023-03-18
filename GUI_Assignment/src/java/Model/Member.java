package Model;

/**
 * @author LOH XIN JIE
 */
public class Member {

    public int memberId;
    public String memberName;
    public String memberPass;

    public Member(int memberId, String memberName, String memberPass) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPass = memberPass;
    }

    public Member(int memberId) {
        this.memberId = memberId;
    }

    public Member() {
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
        this.memberName = memberName;
    }

    public void setMemberPass(String memberPass) {
        this.memberPass = memberPass;
    }
}
