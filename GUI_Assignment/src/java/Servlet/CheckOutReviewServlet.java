/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.PaymentController;

import DataAccess.DBTable;
import DataAccess.Mapper.*;
import Model.*;
import Model.PageModel.PaymentModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "CheckOutReviewServlet", urlPatterns = {"/CheckOutReviewServlet"})
public class CheckOutReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutReviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutReviewServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        try {
//            int memberid = 2000;
            Member member = (Member) session.getAttribute("member");
            int memberId = member.getMemberId();

            if (session == null || session.getAttribute("member") == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            if (memberId != 0) {
                //return cartlist and productlist data(if they same - memberid)
                Map<String, List<?>> cartAndProductLists = PaymentController.getCartAndProductLists(memberId);
                List<Cartlist> cartList = (List<Cartlist>) cartAndProductLists.get("cartList");
                List<Product> productList = (List<Product>) cartAndProductLists.get("productList");
                request.setAttribute("clist", cartList);
                request.setAttribute("plist", productList);

                int totalProducts = 0;
                for (Cartlist cartItem : cartList) {
                    totalProducts += cartItem.getCartQuantity();
                }

                request.setAttribute("totalProducts", totalProducts);

                //return memberAddress & addressBook data
                ArrayList<Object> lists = PaymentController.getAddressLists(memberId);
                ArrayList<MemberAddress> mAddress = (ArrayList<MemberAddress>) lists.get(0);
                ArrayList<AddressBook> addressBook = (ArrayList<AddressBook>) lists.get(1);

                request.setAttribute("memberAddress", mAddress);
                request.setAttribute("addressBook", addressBook);

                //calculate total something
                ArrayList<Cartlist> cart = (ArrayList<Cartlist>) request.getAttribute("clist");
                ArrayList<Product> product = (ArrayList<Product>) request.getAttribute("plist");

                for (Cartlist cartItem : cartList) {
                    totalProducts += cartItem.getCartQuantity();
                    ArrayList<Object> pcondition = new ArrayList<>();
                    pcondition.add(cartItem.getProduct().getProductId());

                    DBTable db = new DBTable();
                    // Check if product has discount
                    List<Discount> discountList = db.Discount.getData(new DiscountMapper(), pcondition, "SELECT * FROM DISCOUNT WHERE product_id = ?");

                    if (discountList.size() > 0) {
                        request.setAttribute("dlist", discountList);
                    }
                }

                //get address id
                String sId = (String) session.getAttribute("sId");
                ArrayList<Object> scondition = new ArrayList<>();
                scondition.add(sId);

                DBTable db = new DBTable();
                // Check if product has discount
                List<AddressBook> shipAddress = db.AddressBook.getData(new AddressBookMapper(), scondition, "SELECT * FROM ADDRESSBOOK WHERE address_id = ?");

                if (shipAddress.size() > 0) {
                    request.setAttribute("slist", shipAddress);
                }

                // get shipping method from session
                String shippingMethod = (String) session.getAttribute("shippingMethod");

                // calculate grand total (including any discounts)
                double grandTotal = PaymentController.calculateGrandTotal(cart, product);

                // calculate tax
                double tax = PaymentController.calculateTax(grandTotal);

                // calculate shipping charge
                double shippingCharge = PaymentController.calculateShippingCharge(shippingMethod);

                // calculate delivery fee
                double deliveryFee = PaymentController.calculateDeliveryFee(grandTotal);

                // calculate final total
                double finalTotal = PaymentController.calculateFinalTotal(grandTotal, tax, shippingCharge, deliveryFee);

                // set attributes for displaying in JSP
                session.setAttribute("grandTotal", grandTotal);
                session.setAttribute("tax", tax);
                session.setAttribute("shippingCharge", shippingCharge);
                session.setAttribute("deliveryFee", deliveryFee);
                session.setAttribute("finalTotal", finalTotal);
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CheckOut/CheckOut.jsp");
        dispatcher.forward(request, response);
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
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        try {
//            int memberId = 2000;
            Member member = (Member) session.getAttribute("member");
            int memberId = member.getMemberId();

            if (session == null || session.getAttribute("member") == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            if (memberId != 0) {
                Date currentDate = new Date(); // Get the current date

                String paymentMethod = (String) session.getAttribute("paymentMethod");

                double total = (Double) session.getAttribute("finalTotal");
                double tax = (Double) session.getAttribute("tax");
                double deliveryFee = (Double) session.getAttribute("deliveryFee");
                double shippingCharge = (Double) session.getAttribute("shippingCharge");

                String sId = (String) session.getAttribute("sId");
                int addId = Integer.parseInt(sId);

                PaymentController p = new PaymentController();
                boolean check = p.addO(currentDate, paymentMethod, total, tax, deliveryFee, shippingCharge, memberId, addId);

                if (check) {
                    //sql get order id
                    String sql = "SELECT * FROM ORDERS "
                            + "WHERE orders_date = ? "
                            + "AND orders_payment_type = ? "
                            + "AND orders_ttlprice = ? "
                            + "AND orders_tax = ? "
                            + "AND orders_delivery_fee = ? "
                            + "AND orders_express_shipping = ? "
                            + "AND member_id = ? "
                            + "AND address_id = ? "
                            + "AND orders_id = (SELECT MAX(orders_id) FROM ORDERS "
                            + "WHERE orders_date = ? "
                            + "AND orders_payment_type = ? "
                            + "AND orders_ttlprice = ? "
                            + "AND orders_tax = ? "
                            + "AND orders_delivery_fee = ? "
                            + "AND orders_express_shipping = ? "
                            + "AND member_id = ? "
                            + "AND address_id = ?)";

                    ArrayList<Object> params = new ArrayList<>();
                    params.add(currentDate);
                    params.add(paymentMethod);
                    params.add(total);
                    params.add(tax);
                    params.add(deliveryFee);
                    params.add(shippingCharge);
                    params.add(memberId);
                    params.add(addId);
                    params.add(currentDate);
                    params.add(paymentMethod);
                    params.add(total);
                    params.add(tax);
                    params.add(deliveryFee);
                    params.add(shippingCharge);
                    params.add(memberId);
                    params.add(addId);

                    Orders o = new DBTable().Orders.getData(new OrdersMapper(), params, sql).get(0);

                    if (o != null) {
                        //order id
                        int orderId = o.getOrdersId();
                        session.setAttribute("orderId", orderId);

                        //get product detail
                        Map<String, List<?>> cartAndProductLists = PaymentController.getCartAndProductLists(memberId);
                        ArrayList<Cartlist> cartList = (ArrayList<Cartlist>) cartAndProductLists.get("cartList");
                        ArrayList<Product> productList = (ArrayList<Product>) cartAndProductLists.get("productList");

                        ArrayList<PaymentModel> cartItems = PaymentController.getCartItem(cartList, productList);

                        for (PaymentModel cartItem : cartItems) {
                            int productId = cartItem.getProduct().getProductId();
                            int ordersQuantity = cartItem.getCartQuantity();

                            PaymentController payController = new PaymentController();
                            double discountedPrice = payController.getDiscount(productList, productId);

                            double subPrice = (discountedPrice * cartItem.getCartQuantity());
                            boolean checkO = p.addOrderlist(orderId, productId, ordersQuantity, subPrice);

                            if (checkO) {
                                response.sendRedirect("CheckOut/ThankYou.jsp");
                            }
                        }
                    }

                }

            }
        } catch (Exception ex) {
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
