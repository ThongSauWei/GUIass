package Servlet;

import Controller.PaymentController;
import DataAccess.DBTable;
import DataAccess.Mapper.*;
import Model.*;
import Model.PageModel.PaymentModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    //return the payment details data
    public static void returnPaymentDetails(HttpServletRequest request, HttpSession session, int memberId) throws SQLException {
        try {
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
                    ArrayList<Object> pcondition = new ArrayList<>();
                    pcondition.add(cartItem.getProduct().getProductId());

                    DBTable db = new DBTable();
                    // Check if product has discount
                    List<Discount> discountList = db.Discount.getData(new DiscountMapper(), pcondition, "SELECT * FROM DISCOUNT WHERE product_id = ?");

                    if (!discountList.isEmpty()) {
                        request.setAttribute("dlist", discountList);
                    }
                }

                //calculate total something
                ArrayList<Cartlist> cart = (ArrayList<Cartlist>) request.getAttribute("clist");
                ArrayList<Product> product = (ArrayList<Product>) request.getAttribute("plist");

                session.setAttribute("totalProducts", totalProducts);

                // calculate grand total
                double grandTotal = PaymentController.calculateGrandTotal(cart, product);

                // calculate tax
                double tax = PaymentController.calculateTax(grandTotal);

                // calculate delivery fee
                double deliveryFee = PaymentController.calculateDeliveryFee(grandTotal);

                // calculate final total
                // set attributes for displaying in JSP
                session.setAttribute("totalProducts", totalProducts);
                session.setAttribute("grandTotal", grandTotal);
                session.setAttribute("tax", tax);
                session.setAttribute("deliveryFee", deliveryFee);
            }
        } catch (SQLException ex) {
            //on here only display error message at console
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to retrieve member addresses and address books
    public static void retrieveMemberAddressesAndBooks(int memberId, HttpServletRequest request) {
        try {
            DBTable db = new DBTable();

            if (memberId != 0) {
                //get Cart id 
                ArrayList<Object> condition = new ArrayList<>();
                condition.add(new Integer(memberId));
                MemberAddress mAddress = new DBTable().MemberAddress.getData(new MemberAddressMapper(), condition,
                        "SELECT * FROM MEMBER_ADDRESS WHERE member_id = ?").get(0);

                Member member = new DBTable().Member.getData(new MemberMapper(), condition, "SELECT * FROM MEMBER WHERE member_id = ?").get(0);

                if (mAddress == null) {
                    //no data found
                } else {
                    ArrayList<MemberAddress> mlist = db.MemberAddress.getData(new MemberAddressMapper(), memberId);

                    ArrayList<AddressBook> alist = new ArrayList<>();

                    for (MemberAddress memberlist : mlist) {
                        AddressBook a = db.AddressBook.getData(new AddressBookMapper(), memberlist.getAddress().getAddressId()).get(0);
                        if (a != null) {
                            alist.add(a);
                        } else {
                            //no data found
                        }
                    }
                    request.setAttribute("mlist", mlist);
                    request.setAttribute("alist", alist);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet test at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int memberId = 2000;
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        int memberId = member.getMemberId();
        retrieveMemberAddressesAndBooks(memberId, request);
        try {
            returnPaymentDetails(request, session, memberId);
        } catch (SQLException ex) {
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Payment/Payment.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String errorMessage = null;

//        int memberid = 2000;
        Member member = (Member) session.getAttribute("member");
        int memberId = member.getMemberId();
        try {

            if (memberId != 0) {
                // Search for member's addresses
                ArrayList<Object> lists = PaymentController.getAddressLists(memberId);
                ArrayList<MemberAddress> mAddress = (ArrayList<MemberAddress>) lists.get(0);
                ArrayList<AddressBook> addressBook = (ArrayList<AddressBook>) lists.get(1);

                // Combine member addresses and address book entries
                ArrayList<PaymentModel> addressItems = PaymentController.getAddressItems(mAddress, addressBook);

                String shippingAddress = request.getParameter("shippingAddress");

                if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
                    errorMessage = "Please select a shipping address.";
                } else {
                    session.setAttribute("sId", shippingAddress);
                }

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

        String paymentMethod = request.getParameter("paymentMethod");
        String shippingMethod = request.getParameter("shippingMethod");

        // Store input data in session
        session.setAttribute("paymentMethod", paymentMethod);
        session.setAttribute("shippingMethod", shippingMethod);

        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            errorMessage = "Please select a payment method.";
        } else if (shippingMethod == null || shippingMethod.trim().isEmpty()) {
            errorMessage = "Please select a shipping method.";
        }

        // If there is an error, set an attribute and redirect to p.jsp
        if (errorMessage != null) {
            //get back the value(Payment.jsp)
//            int memberId = 2000;
            retrieveMemberAddressesAndBooks(memberId, request);

            try {
                returnPaymentDetails(request, session, memberId);
            } catch (SQLException ex) {
                request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
            }

            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/Payment/Payment.jsp").forward(request, response);
        } else {
//            Member member = (Member) session.getAttribute("member");
//            int memberId = member.getMemberId();
            // No errors, redirect to appropriate page
            if (paymentMethod.equals("creditCard")) {
                try {
//                    int memberid = 2000;
                    if (memberId != 0) {
                        //return cartlist and productlist data(if they same - memberid)
                        Map<String, List<?>> cartAndProductLists = PaymentController.getCartAndProductLists(memberId);
                        List<Cartlist> cartList = (List<Cartlist>) cartAndProductLists.get("cartList");
                        List<Product> productList = (List<Product>) cartAndProductLists.get("productList");
                        request.setAttribute("clist", cartList);
                        request.setAttribute("plist", productList);

                        //return memberAddress & addressBook data
                        ArrayList<Object> lists = PaymentController.getAddressLists(memberId);
                        ArrayList<MemberAddress> mAddress = (ArrayList<MemberAddress>) lists.get(0);
                        ArrayList<AddressBook> addressBook = (ArrayList<AddressBook>) lists.get(1);

                        request.setAttribute("memberAddress", mAddress);
                        request.setAttribute("addressBook", addressBook);

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
                try {
                    returnPaymentDetails(request, session, memberId);
                } catch (SQLException ex) {
                    request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                    request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                    response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
                }
                response.sendRedirect("PaymentMethodServlet");

            } else if (paymentMethod.equals("cash")) {
                try {
//                    int memberid = 2000;
                    if (memberId != 0) {
                        //return cartlist and productlist data(if they same - memberid)
                        Map<String, List<?>> cartAndProductLists = PaymentController.getCartAndProductLists(memberId);
                        List<Cartlist> cartList = (List<Cartlist>) cartAndProductLists.get("cartList");
                        List<Product> productList = (List<Product>) cartAndProductLists.get("productList");
                        request.setAttribute("clist", cartList);
                        request.setAttribute("plist", productList);

                        //return memberAddress & addressBook data
                        ArrayList<Object> lists = PaymentController.getAddressLists(memberId);
                        ArrayList<MemberAddress> mAddress = (ArrayList<MemberAddress>) lists.get(0);
                        ArrayList<AddressBook> addressBook = (ArrayList<AddressBook>) lists.get(1);

                        request.setAttribute("memberAddress", mAddress);
                        request.setAttribute("addressBook", addressBook);
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

//                HttpSession session = request.getSession();
                try {
                    returnPaymentDetails(request, session, memberId);
                } catch (SQLException ex) {
                    request.getSession().setAttribute("UnexceptableError", ex.getMessage());
                    request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
                    response.sendRedirect("/GUI_Assignment/Home/view/ErrorPage.jsp");
                }

                response.sendRedirect("PaymentMethodServlet");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
