package Servlet;

/**
 * @author LOH XIN JIE
 */
import Controller.HomeController;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import DataAccess.*;
import DataAccess.Mapper.ProductMapper;
import Model.*;
import java.util.*;
import java.sql.*;

public class SearchServlet extends HttpServlet {

    private DBTable db;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get user search data
            String search = (String) request.getParameter("search");

            //generate sql
            String query = "SELECT * FROM PRODUCT WHERE product_name LIKE '%" + search + "%'";

            //get data
            ArrayList<Product> plist = db.Product.getData(new ProductMapper(), new ArrayList<Object>(), query);

            request.setAttribute("searchResult", plist);

            //loop plist to get the rating value
            HashMap<Integer, Double> ratingList = new HashMap<>();
            for (Product p : plist) {
                double rate = HomeController.getProductRate(HomeController.getProductRatingList(db, p.getProductId()));
                ratingList.put(p.getProductId(), rate);
            }

            request.setAttribute("ratingList", ratingList);

            request.getRequestDispatcher("/Home/view/SearchResult.jsp").forward(request, response);
        } catch (SQLException ex) {
            //turn to error page
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init() throws ServletException {
        this.db = new DBTable();
    }

}
