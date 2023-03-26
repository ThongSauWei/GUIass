/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.Mapper.StaffMapper;
import Model.Staff;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeet
 */
public class StaffController {
    public boolean dltStaff(int id) {
        try {
            DBTable dbTable = new DBTable();

            
            return dbTable.Staff.Delete(new StaffMapper(), new Staff(id));
        } catch (SQLException ex) {
            Logger.getLogger(MemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStaff(String s_id, String name, String password, String ic, String phone_num, String email, String s_date) {
        try {
            int id = Integer.parseInt(s_id);
            Date date = stringToDate(s_date, "yyyy-mm-dd");
            return new DBTable().Staff.Update(new StaffMapper(), new Staff(id, name, password, ic, phone_num, email, date));
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Staff getLatestStaff() {
        try {
            return new DBTable().Staff.getData(new StaffMapper(), new ArrayList<>(), "SELECT * FROM staff ORDER BY staff_id desc FETCH FIRST 1 ROWS ONLY").get(0);
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean addStaff(String name, String password, String ic, String phone_num, String email, String s_date) {
        try {
            Date date = stringToDate(s_date, "yyyy-mm-dd");
            return new DBTable().Staff.Add(new StaffMapper(), new Staff(name, password, ic, phone_num, email, date));
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Staff> getStaff(String search) {
        DBTable dbTable = new DBTable();
        try {
            if (search == null) {
                return dbTable.getStaff().getData(new StaffMapper());
            } else {
                ArrayList<Staff> sfgs = dbTable.getStaff().getData(new StaffMapper());
                ArrayList<Staff> staffs = new ArrayList<>();
                search = search.toLowerCase();
                for (int i = 0; i < sfgs.size(); i++) {
                    if (Integer.toString(sfgs.get(i).getStaffId()).contains(search) || sfgs.get(i).getStaffName().toLowerCase().contains(search)
                            || sfgs.get(i).getStaffEmail().toLowerCase().contains(search) || sfgs.get(i).getStaffPhNo().contains(search) || sfgs.get(i).getStaffIc().contains(search)) {
                        staffs.add(sfgs.get(i));
                    }
                }
                return staffs;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public static Date stringToDate(String dateString, String format) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateString);
    }
}
