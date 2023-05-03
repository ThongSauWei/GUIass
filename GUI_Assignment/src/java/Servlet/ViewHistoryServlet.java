/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DataAccess.DBTable;
import DataAccess.Mapper.OrderlistMapper;
import DataAccess.Mapper.OrdersMapper;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "ViewHistoryServlet", urlPatterns = {"/ViewHistoryServlet"})
public class ViewHistoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewHistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewHistoryServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        int memberId = 2000;
        String sql = "SELECT * FROM ORDERS WHERE member_id = ? ";

        ArrayList<Object> params = new ArrayList<>();
        params.add(memberId);

        try {
            ArrayList<Orders> orders = new DBTable().Orders.getData(new OrdersMapper(), params, sql);
            request.setAttribute("orderlist", orders);

//            for (Orders o : orders) {
//                String sql1 = "SELECT * FROM ORDERLIST WHERE orders_id = ? ";
//
//                ArrayList<Object> condition = new ArrayList<>();
//                condition.add(o.getOrdersId());
//                ArrayList<Orderlist> oList = new DBTable().Orderlist.getData(new OrderlistMapper(), condition, sql);
//                request.setAttribute("oList", oList);
//            }
            ArrayList<Orderlist> oList = new ArrayList<>();
            for (Orders o : orders) {
                String sql1 = "SELECT * FROM ORDERLIST WHERE orders_id = ? ";

                ArrayList<Object> condition = new ArrayList<>();
                condition.add(o.getOrdersId());
                ArrayList<Orderlist> ol = new DBTable().Orderlist.getData(new OrderlistMapper(), condition, sql1);
                oList.addAll(ol);
            }
            request.setAttribute("oList", oList);

            request.getRequestDispatcher("viewHistory.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String productId = request.getParameter("productId");
        String orderId = request.getParameter("orderId");
        System.out.println("productId = " + productId);
        response.sendRedirect("RateReviewServlet?productId=" + productId + "&orderId=" + orderId);

//        response.sendRedirect("RateReviewServlet?productId=" + productId);
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
