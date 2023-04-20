/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DataAccess.DBTable;
import DataAccess.Mapper.CartMapper;
import DataAccess.Mapper.CartlistMapper;
import Model.Cart;
import Model.Cartlist;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author guoc7
 */
public class addToCartPerOne extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addToCartPerOne</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCartPerOne at " + request.getContextPath() + "</h1>");
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
        DBTable db = new DBTable();

        request.getSession().setAttribute("memberId", 2000);
        try {
            int cartId = -1;
            HttpSession session = request.getSession();
            int memberId = (Integer) session.getAttribute("memberId");
//        getMemberId
            String sqlstmt = "SELECT * FROM CART Where MEMBER_ID=?";
            ArrayList<Object> list = new ArrayList();
            list.add(new Integer(memberId));
            List<Cart> cart;
//        getCartId
            cart = db.Cart.getData(new CartMapper(), list, sqlstmt);
            for (int i = 0; i < cart.size(); i++) {
                Cart cartSearch = cart.get(i);
                if (cartSearch.getMember().getMemberId() == memberId) {
                     cartId = cartSearch.getCartId();
                }
            }
            int productId = Integer.parseInt(request.getParameter("menu-list-one"));
            int cartQuantity = 1;

            //set sql item values;
            ArrayList<Object> parameters = new ArrayList<Object>();
            String sql = "Select * From Cartlist WHERE CART_ID=? and PRODUCT_ID=?";
            parameters.add(cartId);
            parameters.add(productId);
            String previousUrl = request.getHeader("Referer");

            List<Cartlist> cartListChecking = db.Cartlist.getData(new CartlistMapper(), parameters, sql);
            if (cartListChecking != null && cartListChecking.size() > 0) {
                Cartlist cartList = cartListChecking.get(0);
                int newQuantity = cartList.getCartQuantity() + 1;
                cartList.setCartQuantity(newQuantity);
                db.Cartlist.Update(new CartlistMapper(), cartList);
                response.sendRedirect(previousUrl);
            } else {
                Cartlist cartlist = new Cartlist(new Cart(cartId), new Product(productId), cartQuantity);
                db.Cartlist.Add(new CartlistMapper(), cartlist);
                response.sendRedirect(previousUrl);
            }

        } catch (SQLException ex) {
            Logger.getLogger(addToCartPerOne.class.getName()).log(Level.SEVERE, null, ex);
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
