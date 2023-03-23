package Model;

import DataAccess.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LOH XIN JIE
 */
public class Staff extends DBModel {

    private int staffId;
    private String staffName;
    private String staffPass;
    private String staffIc;
    private String staffPhNo;
    private String staffEmail;
    private Date staffBirthdate;

    public Staff(int staffId, String staffName, String staffPass, String staffIc, String staffPhNo, String staffEmail, Date staffBirthdate) {
        super("staff");
        this.staffId = staffId;
        this.staffName = staffName != null ? staffName : "";
        this.staffPass = staffPass != null ? staffPass : "";
        this.staffIc = staffIc != null ? staffIc : "";
        this.staffPhNo = staffPhNo != null ? staffPhNo : "";
        this.staffEmail = staffEmail != null ? staffEmail : "";
        this.staffBirthdate = staffBirthdate != null ? staffBirthdate : new Date(253402214400000L);
    }

    public Staff(String staffName, String staffPass, String staffIc, String staffPhNo, String staffEmail, Date staffBirthdate) {
        super("staff");
        this.staffName = staffName != null ? staffName : "";
        this.staffPass = staffPass != null ? staffPass : "";
        this.staffIc = staffIc != null ? staffIc : "";
        this.staffPhNo = staffPhNo != null ? staffPhNo : "";
        this.staffEmail = staffEmail != null ? staffEmail : "";
        this.staffBirthdate = staffBirthdate != null ? staffBirthdate : new Date(253402214400000L);
    }

    public Staff(int staffId) {
        super("staff");
        this.staffId = staffId;
    }

    public Staff() {
        super("staff");
        this.staffName = "";
        this.staffPass = "";
        this.staffIc = "";
        this.staffPhNo = "";
        this.staffEmail = "";
        this.staffBirthdate = new Date(253402214400000L);
    }

    public int getStaffId() {
        return staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public String getStaffIc() {
        return staffIc;
    }

    public String getStaffPhNo() {
        return staffPhNo;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public Date getStaffBirthdate() {
        return staffBirthdate;
    }

    public String getDisplayFormatBirthdate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(staffBirthdate);
    }

    public String getEditFormatBirthdate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(staffBirthdate);
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName != null ? staffName : "";
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass != null ? staffPass : "";
    }

    public void setStaffIc(String staffIc) {
        this.staffIc = staffIc != null ? staffIc : "";
    }

    public void setStaffPhNo(String staffPhNo) {
        this.staffPhNo = staffPhNo != null ? staffPhNo : "";
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail != null ? staffEmail : "";
    }

    public void setStaffBirthdate(Date staffBirthdate) {
        this.staffBirthdate = staffBirthdate != null ? staffBirthdate : new Date(253402214400000L);
    }
}
