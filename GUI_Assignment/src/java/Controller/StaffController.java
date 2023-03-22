/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.Mapper.StaffMapper;
import Model.Staff;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yeet
 */
public class StaffController {
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
}
