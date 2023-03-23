/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DBTable;
import DataAccess.Mapper.ProductMapper;
import Model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yeet
 */
public class prodController {

    public static void main(String[] args) {
        System.out.println(new prodController().getProds("air"));
    }

    public boolean updateProd(String s_id, String name, String desc, double price, char active) throws SQLException {
        int id = Integer.parseInt(s_id);
        try {
            return new DBTable().Product.Update(new ProductMapper(), new Product(id, name, desc, price, active));
        } catch (SQLException ex) {
            Logger.getLogger(prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addProd(String name, String desc, double price, char active) {
        try {
            return new DBTable().Product.Add(new ProductMapper(), new Product(name, desc, price, active));
        } catch (SQLException ex) {
            Logger.getLogger(prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Product getLatestProd() {
        try {
            return new DBTable().Product.getData(new ProductMapper(), new ArrayList<>(), "SELECT * FROM product ORDER BY product_id desc FETCH FIRST 1 ROWS ONLY").get(0);
        } catch (SQLException ex) {
            Logger.getLogger(prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Product> getProds(String search) {
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

    public ArrayList<Product> getProds(String search, int status) {
        ArrayList<Product> products = this.getProds(search);
        if (status == 1) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductActive() != '1') {
                    products.remove(i);
                    i--;
                }
            }
        } else if (status == 0) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductActive() != '0') {
                    products.remove(i);
                    i--;
                }
            }
        }

        return products;
    }

    public Product getProd(String id) {
        return getProd(Integer.parseInt(id));
    }

    public Product getProd(int id) {
        try {
            ArrayList<Product> products = new DBTable().Product.getData(new ProductMapper(), id);
            if (products.size() > 0) {
                return products.get(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(prodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
