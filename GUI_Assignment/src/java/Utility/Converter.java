/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author LENOVO
 */
public class Converter {

    public static java.util.Date convertSQLDateToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date convertUtilDateToSQLDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
