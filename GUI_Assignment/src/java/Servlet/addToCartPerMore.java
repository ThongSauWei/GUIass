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
import Model.Member;
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
public class addToCartPerMore extends HttpServlet {

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
            out.println("<title>Servlet addToCartPerMore</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCartPerMore at " + request.getContextPath() + "</h1>");
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

//        Member mem = new Member();
////        mem=null;
//        mem.setMemberId(2001);
//        request.getSession().setAttribute("member", mem); 
        try {
            DBTable db = new DBTable();
            int ahrefProductId = Integer.parseInt(request.getParameter("hrefId"));
            double ahrefRating = Double.parseDouble(request.getParameter("hrefRating"));

            int productId = Integer.parseInt(request.getParameter("productId"));
            int cartQuantity = Integer.parseInt(request.getParameter("quantity"));
            int cartId = -1;
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute("member") == null) {
                response.sendRedirect("/login/login.jsp"); // Redirect to login.jsp if session is null or memberRole is null
                return; // Return to prevent further execution of the code
            }
            Member member = (Member) session.getAttribute("member");
            int memberId = member.getMemberId();

//        getMemberId
            String sqlstmt = "SELECT * FROM CART Where MEMBER_ID=?";
            ArrayList<Object> list = new ArrayList();
            list.add(memberId);
            List<Cart> cart;
//        getCartId
            cart = db.Cart.getData(new CartMapper(), list, sqlstmt);
            for (int i = 0; i < cart.size(); i++) {
                Cart cartSearch = cart.get(i);
                if (cartSearch.getMember().getMemberId() == memberId) {
                    cartId = cartSearch.getCartId();
                }
            }
            //set sql item values;
            ArrayList<Object> parameters = new ArrayList<Object>();
            String sql = "Select * From Cartlist WHERE CART_ID=? and PRODUCT_ID=?";
            parameters.add(cartId);
            parameters.add(productId);
            List<Cartlist> cartListChecking = db.Cartlist.getData(new CartlistMapper(), parameters, sql);
            if (cartListChecking != null && cartListChecking.size() > 0) {
                Cartlist cartList = cartListChecking.get(0);
                int newQuantity = cartList.getCartQuantity() + cartQuantity;
                cartList.setCartQuantity(newQuantity);
                db.Cartlist.Update(new CartlistMapper(), cartList);
                response.sendRedirect("viewProductServlet?id=" + ahrefProductId + "&avgRating=" + ahrefRating);

            } else {
                Cartlist cartlist = new Cartlist(new Cart(cartId), new Product(productId), cartQuantity);
                db.Cartlist.Add(new CartlistMapper(), cartlist);
                response.sendRedirect("viewProductServlet?id=" + ahrefProductId + "&avgRating=" + ahrefRating);
            }
        } catch (SQLException ex) {
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
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
