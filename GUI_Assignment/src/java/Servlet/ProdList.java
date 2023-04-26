///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Servlet;
//
//import Controller.prodController;
//import Model.Product;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Yeet
// */
//public class ProdList extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            String search = request.getParameter("search") == null ? "" : request.getParameter("search");
//            int status = request.getParameter("status") == null ? 1 : Integer.parseInt(request.getParameter("status"));
//            ArrayList<Product> products = new prodController().getProds(search, status);
//            if (products == null) {
//                response.sendRedirect("unexpected_error.jsp");
//            } else if (products.isEmpty()) {
//                out.print("<td colspan=6>No Record.</td>");
//            }
//            for (Product product : products) {
//                out.print("<tr>");
//                out.print("<td>" + product.getProductId() + "</td>");
//                out.print("<td>" + product.getProductName() + "</td>");
//                out.print("<td>" + product.getProductDesc() + "</td>");
//                out.print("<td>" + product.getProductPrice() + "</td>");
//                out.print("<td><input type=\"checkbox\" " + (product.getProductActive() == '1' ? "checked" : "") + " disabled></td>");
//                out.print("<td><a href=\"../../ProdMaint?id=" + product.getProductId() + "&isNew=false\" style=\"font-size:20px;color:grey\" class=\"fa\"><i class=\"edit fa fa-pencil\"></i></a></td>");
//                out.print("</tr>");
//            }
//        } catch (SQLException ex) {
//            response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//}
