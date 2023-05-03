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
import Utility.*;

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
            request.getSession().setAttribute("UnexceptableError", "orders is null");
            request.getSession().setAttribute("UnexceptableErrorDesc", "Unexpected Error");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
            return;
        } else if (orders.isEmpty()) {
            out.println("<td colspan=7>No Record.</td>");
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
        if (CheckPermission.permissionStaff(request)) {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                request.getSession().setAttribute("UnexceptableErrorDesc", "Unexpected Error");
                request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
            }
        } else if (CheckPermission.permissionNoLogin(request)) {
            request.getRequestDispatcher("login/staffLogin.jsp").forward(request, response);
        } else {
            //turn to error page , reason - premission denied
            request.getRequestDispatcher("Home/view/PermissionDenied.jsp").forward(request, response);
        }
    }

}
