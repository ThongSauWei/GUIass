/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.Mapper.AddressBookMapper;
import DataAccess.Mapper.MemberAddressMapper;
import DataAccess.Mapper.MemberMapper;
import Model.AddressBook;
import Model.Member;
import Model.MemberAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeet
 */
public class MemController {

    public static void main(String[] args) {

        System.out.print(new MemController().getMem("John"));
    }

    public ArrayList<Member> getMem(String search) {
        DBTable dbTable = new DBTable();
        try {
            if (search == null) {
                return dbTable.getMember().getData(new MemberMapper());
            } else {
                ArrayList<Member> sfgs = dbTable.getMember().getData(new MemberMapper());
                    ArrayList<Member> members = new ArrayList<>();
                for (int i = 0; i < sfgs.size(); i++) {
                        if (Integer.toString(sfgs.get(i).getMemberId()).contains(search) || sfgs.get(i).getMemberName().toLowerCase().contains(search.toLowerCase())) {
                            members.add(sfgs.get(i));
                        }
                }
                return members;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean dltMem(int id) {
        try {
            DBTable dbTable = new DBTable();

            ArrayList<Member> sdfasd = dbTable.Member.getData(new MemberMapper(), id);
            Member member = new Member();
            if (sdfasd.size() == 1) {
                member = sdfasd.get(0);
            }
            ArrayList<MemberAddress> memAdds = dbTable.MemberAddress.getData(new MemberAddressMapper(), member.getMemberId());
            ArrayList<AddressBook> addressbooks = new ArrayList<>();

            for (MemberAddress memAdd : memAdds) {
                ArrayList<AddressBook> faslkdfl = dbTable.AddressBook.getData(new AddressBookMapper(), memAdd.getAddress().getAddressId());
                if (faslkdfl.size() == 1) {
                    addressbooks.add(dbTable.AddressBook.getData(new AddressBookMapper(), faslkdfl.get(0).getAddressId()).get(0));
                }
                dbTable.MemberAddress.Delete(new MemberAddressMapper(), memAdd);
            }

            for (AddressBook addressbook : addressbooks) {
                dbTable.AddressBook.Delete(new AddressBookMapper(), addressbook);
            }

            //new DBTable().MemberAddress.Delete(new MemberAddressMapper(), new MemberAddress(member));
            //    dbTable.AddressBook.Delete(new AddressBookMapper(), new AddressBook());
            return dbTable.Member.Delete(new MemberMapper(), member);
        } catch (SQLException ex) {
            Logger.getLogger(MemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean dltMem(String id) {
        return dltMem(Integer.parseInt(id));
    }
}
