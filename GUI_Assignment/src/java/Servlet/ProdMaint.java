/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.prodController;
import Model.Product;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yeet
 */
public class ProdMaint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isNew = request.getParameter("isNew").equals("true");
        if (!isNew) {
            try {
                Product product;
                String id = request.getParameter("id");
                product = new prodController().getProd(id);
                if (product != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("product", product);
                    response.sendRedirect("/GUI_Assignment/admin/view/prod_maint.jsp?id=" + product.getProductId() + "&isNew=false");
                } else {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }
            } catch (SQLException ex) {
                response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = null;

        int action = request.getParameter("action") == null ? 0 : Integer.parseInt(request.getParameter("action"));
        if (request.getParameter("submit") != null) {
            int submit = Integer.parseInt(request.getParameter("submit"));
            if (submit == 1) {
                try {
                    String name = request.getParameter("name");
                    String desc = request.getParameter("description");
                    double price = Double.parseDouble(request.getParameter("price"));
                    char active = request.getParameter("active").charAt(0);

                    if (new prodController().addProd(name, desc, price, active)) {
                        if (action == 1) {
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_list.jsp");
                            return;
                        }
                        if (action == 2) {
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_maint.jsp?isNew=true&action=" + action + "");
                            return;
                        }
                        if (action == 3) {
                            String id = request.getParameter("id");
                            product = new prodController().getLatestProd();
                            if (product != null) {
                                HttpSession session = request.getSession();
                                session.setAttribute("product", product);
                            }
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_maint.jsp?isNew=false&action=" + action + "&isSaved=true&id=" + product.getProductId() + "");
                        }
                    } else {
                        response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                    }
                } catch (SQLException ex) {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }
            } else if (submit == 0) {

                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String desc = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                char active = request.getParameter("active") != null ? request.getParameter("active").charAt(0) : '0';

                try {
                    if (new prodController().updateProd(id, name, desc, price, active)) {
                        if (action == 1) {
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_list.jsp");
                            return;
                        }
                        if (action == 2) {
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_maint.jsp?isNew=true&action=" + action + "");
                            return;
                        }
                        if (action == 3) {
                            product = new prodController().getProd(id);
                            if (product != null) {
                                HttpSession session = request.getSession();
                                session.setAttribute("product", product);
                            }
                            response.sendRedirect("/GUI_Assignment/admin/view/prod_maint.jsp?isNew=false&&action=" + action + "&isSaved=true&id=" + id + "");
                        }
                    } else {
                        response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                    }
                } catch (SQLException ex) {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }
            }
        }

    }
}
