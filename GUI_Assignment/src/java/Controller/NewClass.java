/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.DbSet;
import DataAccess.Mapper.ProductMapper;
import Model.Product;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class NewClass {
    public static void main(String[] args) {
        try {
            DBTable db = new DBTable();
            DbSet<Product> product = db.Product;
            ArrayList<Product> plist = product.getData(new ProductMapper());
            for (Product p : plist) {
                System.out.println(p.getProductName());

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
