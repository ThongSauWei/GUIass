/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.DbSet;
import DataAccess.Mapper.ProductMapper;
import DataAccess.Mapper.StaffMapper;
import Model.Product;
import Model.Staff;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeet
 */
public class prodController {
    public static void main(String[] args) {
        try {
            new DBTable().Staff.Add(new StaffMapper(),
                    new Staff(1003456, "If you read2", "this youre", "123456789012", "2346235623","gay@gmail.com", new Date(1975 - 1900, 11, 17)));
        } catch (SQLException ex) {
            Logger.getLogger(prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Product> getProd(String search) {
        DBTable dbTable = new DBTable();
        try {
            if (search == null) {
                return dbTable.getProduct().getData(new ProductMapper());
            } else {
                ArrayList<Product> sfgs = dbTable.getProduct().getData(new ProductMapper());
                    ArrayList<Product> products = new ArrayList<>();
                for (int i = 0; i < sfgs.size(); i++) {
                        if (Integer.toString(sfgs.get(i).getProductId()).contains(search) || sfgs.get(i).getProductName().toLowerCase().contains(search.toLowerCase())) {
                            products.add(sfgs.get(i));
                        }
                }
                return products;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
}
