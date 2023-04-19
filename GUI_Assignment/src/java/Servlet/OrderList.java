/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.OrderListingController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yeet
 */
public class OrderList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String search = request.getParameter("search") != null ? request.getParameter("search") : "";

        List<HashMap<String, Object>> orders = OrderListingController.getOrdersForList(search);

        if (orders == null) {
            response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
            return;
        } else if (orders.isEmpty()) {
            out.println("<td colspan=5>No Record.</td>");
        }
        for (HashMap<String, Object> order : orders) {
            out.println("<tr>");
            out.println("<td>" + order.get("ORDERS_ID") + "</td>");
            out.println("<td>" + order.get("ORDERS_DATE") + "</td>");
            out.println("<td>" + order.get("MEMBER_ID") + "</td>");
            out.println("<td>" + order.get("MEMBER_NAME") + "</td>");
            out.println("<td>" + order.get("PRODUCT_ID") + "</td>");
            out.println("<td>" + order.get("PRODUCT_NAME") + "</td>");
            out.println("<td>" + order.get("ORDERS_QUANTITY") + "</td>");
            out.println("</tr>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
        }
    }

}
