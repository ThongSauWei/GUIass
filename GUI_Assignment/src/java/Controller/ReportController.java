/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataAccess.DbSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Yeet
 */
public class ReportController {
    public static void main(String[] args) {
        List<HashMap<String, Object>> salesReportResult = salesReport();

// Iterate over the result and print it
for (HashMap<String, Object> row : salesReportResult) {
    System.out.println("Product ID: " + row.get("PRODUCT_ID"));
}
    }

    public static List<HashMap<String, Object>> reportSelector(String query) {
        return DbSet.customizeSqlSelect(query);
    }

    public class Report {

        String query;

        public Report(String query) {
            this.query = query;
        }

        public List<HashMap<String, Object>> salesReport() {
            query = "SELECT\n"
                    + "  p.product_id,\n"
                    + "  p.product_name,\n"
                    + "  SUM(ol.orders_quantity) AS total_units_sold,\n"
                    + "  SUM(ol.orders_subprice) AS total_revenue\n"
                    + "FROM\n"
                    + "  orders o\n"
                    + "  JOIN orderlist ol ON o.orders_id = ol.orders_id\n"
                    + "  JOIN product p ON ol.product_id = p.product_id\n"
                    + "WHERE\n"
                    + "  o.orders_date BETWEEN '2022-01-01' AND '2022-12-31'\n"
                    + "GROUP BY\n"
                    + "  p.product_id,\n"
                    + "  p.product_name\n"
                    + "ORDER BY\n"
                    + "  total_revenue DESC\n"
                    + "";
            return reportSelector(query);
        }

        public List<String> getColumnNames(String sqlQuery) {
            List<String> columnNames = new ArrayList<>();
            Pattern pattern = Pattern.compile("SELECT\\s+(.*?)\\s+FROM", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(sqlQuery);
            if (matcher.find()) {
                String columns = matcher.group(1);
                columns = columns.replaceAll("\\n\\s+", " "); // replace line breaks and indentation with spaces
                for (String column : columns.split(",")) {
                    column = column.trim();
                    if (column.contains(" AS ")) {
                        column = column.split(" AS ")[1];
                    } else {
                        column = column.replaceAll("^[^.]*\\.", ""); // remove table name from column name
                    }
                    column = column.replaceAll("_", " "); // replace underscores with spaces
                    String[] words = column.split(" ");
                    StringBuilder formattedColumn = new StringBuilder();
                    for (String word : words) {
                        formattedColumn.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
                    }
                    columnNames.add(formattedColumn.toString().trim());
                }
            }
            return columnNames;
        }
    }
    public static List<HashMap<String, Object>> salesReport() {
            String query = "SELECT\n"
                    + "  p.product_id,\n"
                    + "  p.product_name,\n"
                    + "  SUM(ol.orders_quantity) AS total_units_sold,\n"
                    + "  SUM(ol.orders_subprice) AS total_revenue\n"
                    + "FROM\n"
                    + "  orders o\n"
                    + "  JOIN orderlist ol ON o.orders_id = ol.orders_id\n"
                    + "  JOIN product p ON ol.product_id = p.product_id\n"
                    + "WHERE\n"
                    + "  o.orders_date BETWEEN '2022-01-01' AND '2022-12-31'\n"
                    + "GROUP BY\n"
                    + "  p.product_id,\n"
                    + "  p.product_name\n"
                    + "ORDER BY\n"
                    + "  total_revenue DESC\n"
                    + "";
            return reportSelector(query);
        }
    
    public static List<String> getColumnNames(String sqlQuery) {
            List<String> columnNames = new ArrayList<>();
            Pattern pattern = Pattern.compile("SELECT\\s+(.*?)\\s+FROM", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(sqlQuery);
            if (matcher.find()) {
                String columns = matcher.group(1);
                columns = columns.replaceAll("\\n\\s+", " "); // replace line breaks and indentation with spaces
                for (String column : columns.split(",")) {
                    column = column.trim();
                    if (column.contains(" AS ")) {
                        column = column.split(" AS ")[1];
                    } else {
                        column = column.replaceAll("^[^.]*\\.", ""); // remove table name from column name
                    }
                    column = column.replaceAll("_", " "); // replace underscores with spaces
                    String[] words = column.split(" ");
                    StringBuilder formattedColumn = new StringBuilder();
                    for (String word : words) {
                        formattedColumn.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
                    }
                    columnNames.add(formattedColumn.toString().trim());
                }
            }
            return columnNames;
        }

}
