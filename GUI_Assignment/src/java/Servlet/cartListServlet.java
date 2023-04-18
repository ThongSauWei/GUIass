/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;


import DataAccess.DBTable;
import DataAccess.Mapper.CartlistMapper;
import DataAccess.Mapper.ProductMapper;
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

/**
 *
 * @author guoc7
 */
public class cartListServlet extends HttpServlet {

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
            out.println("<title>Servlet cartListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cartListServlet at " + request.getContextPath() + "</h1>");
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
        DBTable db = new DBTable();
        try {
            List<Cartlist> cartList = db.Cartlist.getData(new CartlistMapper());
            List<Product> productList = db.Product.getData(new ProductMapper());
            List<Product> sameProductFound = new ArrayList<>();
            //search through the product and cartlist if found then store in arraylist so i can output the product details
            for (int i = 0; i < cartList.size(); i++) {
                for (int y = 0; y < productList.size(); y++) {
                    if (cartList.get(i).getProduct().getProductId() == productList.get(y).getProductId()) {
                        sameProductFound.add(productList.get(y));
                        request.setAttribute("productList", sameProductFound);
                    }
                }
            }
            request.setAttribute("cartList", cartList);
            request.getRequestDispatcher("/productMenu/itemcart.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(cartListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        DBTable db = new DBTable();   
        Cartlist cartDelete = new Cartlist();
        cartDelete.setCart(new Cart(1));
        cartDelete.setProduct(new Product(Integer.parseInt(request.getParameter("deleteProuctId"))));
        cartDelete.setCartQuantity(Integer.parseInt(request.getParameter("pin")));
        //get action from form name
//        String action = request.getParameter("cartAction");
        try {
//            if (action != null && action.equals("cartAction")) {
             
                boolean deleteTrue= db.Cartlist.Delete(new CartlistMapper(),cartDelete);
                if(deleteTrue){
                    response.sendRedirect("cartListServlet");
                }
//            }
        } catch (SQLException ex) {
            Logger.getLogger(cartListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
