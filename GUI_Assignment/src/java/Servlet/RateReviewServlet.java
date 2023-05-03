/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.PaymentController;
import DataAccess.DBTable;
import DataAccess.Mapper.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@WebServlet(name = "RateReviewServlet", urlPatterns = {"/RateReviewServlet"})
public class RateReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet RateReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RateReviewServlet at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");
        int pId = Integer.parseInt(productId);
        request.setAttribute("productID", pId);

        String orderId = request.getParameter("orderId");
        int oId = Integer.parseInt(orderId);

        request.setAttribute("orderID", oId);

        String sql = "SELECT * FROM PRODUCT WHERE product_id = ? ";

        ArrayList<Object> params = new ArrayList<>();
        params.add(pId);

        ArrayList<Product> product;
        try {
            product = new DBTable().Product.getData(new ProductMapper(), params, sql);
            request.setAttribute("product", product);

            request.getRequestDispatcher("/RateReview/rateAndReview.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
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
        HttpSession session = request.getSession();
//        processRequest(request, response);
        String reviewText = request.getParameter("reviewText");
        String rate = request.getParameter("rating");

        String errorMessage = null;
        if (reviewText == null || reviewText.trim().isEmpty()) {
            errorMessage = "Please write review.";
        } else if (rate == null || rate.trim().isEmpty()) {
            errorMessage = "Please give us a rating.";
        }

        //if got error return back
        if (errorMessage != null) {
            String productId = request.getParameter("productId");
            int pId = Integer.parseInt(productId);
            request.setAttribute("productID", pId);

            String orderId = request.getParameter("orderId");
            int oId = Integer.parseInt(orderId);

            request.setAttribute("orderID", oId);

            String sql = "SELECT * FROM PRODUCT WHERE product_id = ? ";

            ArrayList<Object> params = new ArrayList<>();
            params.add(pId);

            ArrayList<Product> product;
            try {
                product = new DBTable().Product.getData(new ProductMapper(), params, sql);
                request.setAttribute("product", product);

            } catch (SQLException ex) {
                request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
            }

            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/RateReview/rateAndReview.jsp").forward(request, response);
        } else {
//            int memberId = 2000;
            Member member = (Member) session.getAttribute("member");
            int memberId = member.getMemberId();
            Date rateDate = new Date();
            try {
                if (memberId != 0) {
                    int rating = Integer.parseInt(rate);
                    PaymentController p = new PaymentController();

                    //productid
                    String productId = request.getParameter("productId");
                    int pId = Integer.parseInt(productId);

                    String orderId = request.getParameter("orderId");
                    int oId = Integer.parseInt(orderId);

                    p.addRateReview(reviewText, rating, rateDate, pId, memberId, oId);

                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException ex) {
                request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
            } catch (Exception ex) {
                request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
            }
        }

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
