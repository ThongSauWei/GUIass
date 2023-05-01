/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.OrderListingController;
import DataAccess.DBTable;
import DataAccess.Mapper.ProductMapper;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author erika
 */
public class salesRecord extends HttpServlet {
    
    private DBTable data;
    
    @Override
    public void init() throws ServletException {
        this.data = new DBTable();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet salesRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet salesRecord at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected ArrayList<Product> filterList(HttpServletRequest request) throws SQLException, ParseException, NumberFormatException {
        String memberID = request.getParameter("memberID") == null ? "" : request.getParameter("memberID");
        String city = request.getParameter("city") == null ? "" : request.getParameter("city");
        String postcode = request.getParameter("city") == null ? "" : request.getParameter("postcode");
        String state = request.getParameter("state") == null ? "" : request.getParameter("state");
        
        
        String sql = "SELECT DISTINCT MEMBER., ORDERS.\n"
                + "FROM PRODUCT\n"
                + "INNER JOIN ORDERLIST ON PRODUCT.PRODUCT_ID = ORDERLIST.PRODUCT_ID\n"
                + "INNER JOIN ORDERS ON ORDERLIST.ORDERS_ID = ORDERS.ORDERS_ID\n"
                + "INNER JOIN MEMBER ON ORDERS.MEMBER_ID = MEMBER.MEMBER_ID\n"
                + "INNER JOIN ADDRESSBOOK ON ORDERS.ADDRESS_ID = ADDRESSBOOK.ADDRESS_ID\n"
                + "WHERE PRODUCT.PRODUCT_ID = ? AND PRODUCT.PRODUCT_ACTIVE = '1'\n"
                + "AND MEMBER.MEMBER_ID = ?\n"
                + "AND ADDRESSBOOK.ADDRESS_CITY LIKE '%X%' \n"
                + "AND ADDRESSBOOK.ADDRESS_POSTCODE LIKE '%x%'\n"
                + "AND ADDRESSBOOK.ADDRESS_STATE LIKE '%x%'";
        
        ArrayList<Object> condition = new ArrayList<>();
        condition.add(new Integer(memberID));
        
        if(!memberID.isEmpty()){
            sql += "AND MEMBER.MEMBER_ID = ?\n";
            condition.add(new Integer(Integer.parseInt(memberID)));
        }
        if(!city.isEmpty()){
            sql += "AND ADDRESSBOOK.ADDRESS_CITY LIKE '%" + city + "%' \n";
        }
        if(!postcode.isEmpty()){
            sql += "AND ADDRESSBOOK.ADDRESS_POSTCODE LIKE '%" + postcode + "%' \n";
        }
        if(!state.isEmpty()){
            sql += "AND ADDRESSBOOK.ADDRESS_STATE LIKE '%" + state + "%' \n";
        }
        
        return data.Product.getData(new ProductMapper(), condition, sql);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
