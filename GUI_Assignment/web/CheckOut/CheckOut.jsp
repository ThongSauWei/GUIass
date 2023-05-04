<%-- 
    Document   : CheckOut
    Created on : Apr 26, 2023, 1:01:39 AM
    Author     : Thong Sau Wei
--%>

<%@page import="Controller.DiscountController"%>
<%@page import="Model.*"%>
<%@page import="Model.PageModel.PaymentModel"%>
<%@page import="Controller.PaymentController"%>
<%@page import="Model.Product"%>
<%@page import="Model.Cartlist"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    ArrayList<Cartlist> cart = (ArrayList<Cartlist>) request.getAttribute("clist");
    ArrayList<Product> product = (ArrayList<Product>) request.getAttribute("plist");

    ArrayList<Discount> discount1 = (ArrayList<Discount>) request.getAttribute("dlist");

    ArrayList<MemberAddress> mAdd = (ArrayList<MemberAddress>) request.getAttribute("memberAddress");
    ArrayList<AddressBook> aBook = (ArrayList<AddressBook>) request.getAttribute("addressBook");

    ArrayList<PaymentModel> cartItems = PaymentController.getCartItem(cart, product);
    String shippingMethod = (String) session.getAttribute("shippingMethod");
    String paymentMethod = (String) session.getAttribute("paymentMethod");
    double dF = (Double) session.getAttribute("deliveryFee");

    //shipping address
    String sId = (String) session.getAttribute("sId");
    ArrayList<AddressBook> sA = (ArrayList<AddressBook>) request.getAttribute("slist");

    int totalProduct1 = (Integer) request.getAttribute("totalProducts");

%>



<jsp:useBean id="discount" class="Controller.DiscountController" scope="application"></jsp:useBean>

    <!DOCTYPE html>
    <html lang="en">
        
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">


            <link rel="stylesheet" href="css/bootstrap.css">
            <link rel="stylesheet" href="/GUI_Assignment/css/CheckOut.css">

           
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        </head>

        <body>
        
        <%@include file="/Home/view/Header.jsp"%>
        <div class="card" style="margin-top: 30px;">
            <div class="row">
                <div class="col-md-8 cart">
                    <div class="title">
                        <div class="row">
                            <div class="col">
                                <h4><b>Check Out Review</b></h4>
                            </div>
                            <div class="col align-self-center text-right text-muted"><%= totalProduct1%> items</div>
                        </div>
                    </div>

                    <div class="row border-top border-bottom">
                        <div class="row main align-items-center">
                            <div class="col-3">Product Image</div>
                            <div class="col-5">
                                <div class="row">Name</div>
                            </div>
                            <div class="col">
                                <div class="col">Qty</div>
                            </div>
                            <div class="col">Price</div>
                        </div>
                    </div>

                    <% for (PaymentModel cartItem : cartItems) {%>
                    <% if (cartItem != null) {%>
                    <div class="row border-bottom">
                        <div class="row main align-items-center">
                            <div class="col-3"><img class="img-fluid" src="RetrieveImageServlet?imageID=<%= cartItem.getProduct().getProductId()%>" width="100" height="100"></div>
                            <div class="col-5">
                                <div class="row"><%= cartItem.getProduct().getProductName()%></div>
                            </div>
                            <div class="col">
                                <div class="col" style="margin-left: 12px;"><%= cartItem.getCartQuantity()%></div>
                            </div>
                            <% double originalPrice = cartItem.getProduct().getProductPrice();
                                double discountedPrice = originalPrice;
                                if(discount1 != null && discount1.size() > 0){
                                    for (Discount d : discount1) {
                                        discountedPrice = discount.getPrice(originalPrice,
                                                d.getDiscountPercentage());
                                    }
                                }
                                if (discountedPrice < originalPrice) {%>
                            <div class="col"><del>RM <%= cartItem.getProduct().getProductPrice()%></del><br> RM <%= discountedPrice%></div>
                                <% } else {%>
                            <div class="col">RM <%= cartItem.getProduct().getProductPrice()%></div>
                            <% } %>
                        </div>
                    </div>
                    <% }%>
                    <% }%>


                </div>
                <div class="col-md-4 summary">
                    <div>
                        <h5><b>Summary</b></h5>
                    </div>
                    <hr>
                    <div class="row" style="color: gray;">
                        <div class="col" style="padding-left:0;">Payment Method</div>
                        <%    if (paymentMethod.equals("creditCard")) {
                        %>
                        <div class="col text-right">Credit Card</div>
                        <%
                            }
                            if (paymentMethod.equals("cash")) {
                        %>
                        <div class="col text-right">Cash</div>
                        <%
                            }
                        %>
                    </div>

                    <form>
                        <%
                            String adName, adPhone, adNo, adStreet, adCity, adState, adPostcode;
                            for (AddressBook ad : sA) {
                                adName = ad.getAddressName();
                                adPhone = ad.getAddressPhone();
                                adNo = ad.getAddressNo();
                                adStreet = ad.getAddressStreet();
                                adCity = ad.getAddressCity();
                                adState = ad.getAddressState();
                                adPostcode = ad.getAddressPostcode();

                        %>


                        <p>SHIPPING ADDRESS</p>
                        <textarea disabled style="width: 340px;height: 50px;"><%= adNo%>, <%= adStreet%>, <%= adState%>, <%= adPostcode%>, <%= adCity%></textarea>


                        <%  }%>


                        <%
                            if (paymentMethod.equals("creditCard")) {
                        %>

                        <p>TYPE OF CARD</p>
                        <input type="text" placeholder="${typeCard}" disabled>

                        <p>NAME</p>
                        <input type="text" placeholder="${cardName}" disabled>

                        <p>CARD NUMBER</p>
                        <input type="text" placeholder="${cardNumber}" disabled>

                        <p>EXPIRATION DATE</p>
                        <input type="text" placeholder="${expirationDate}" disabled>

                        <p>CVV</p>
                        <input type="text" placeholder="${cvv}" disabled>

                        <%
                            }
                            if (paymentMethod.equals("cash")) {
                        %>

                        <p>First Name</p>
                        <input type="text" placeholder="${cashfirstName}" disabled>

                        <p>Last Name</p>
                        <input type="text" placeholder="${cashlastName}" disabled>

                        <p>Email</p>
                        <input type="text" placeholder="${cashemail}" disabled>

                        <%
                            }
                        %>

                    </form>
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 1vh 0;">
                        <div class="col">SUBTOTAL</div>
                        <div class="col text-right">RM <%= session.getAttribute("grandTotal")%></div>
                    </div>

                    <div class="row" style="padding: 1vh 0;">
                        <div class="col">TAX</div>
                        <div class="col text-right">RM <%= session.getAttribute("tax")%></div>
                    </div>

                    <% if (shippingMethod.equals("expressShipping")) {%>
                    <div class="row" style="padding: 1vh 0;">
                        <div class="col">SHIPPING CHARGE</div>
                        <div class="col text-right">RM <%= session.getAttribute("shippingCharge")%></div>
                    </div>
                    <% } %>

                    <%
                        if (dF == 0.0) {
                    %>
                    <div class="row" style="padding: 1vh 0;">
                        <div class="col">DELIVERY FEE</div>
                        <div class="col text-right">Free</div>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="row" style="padding: 1vh 0;">
                        <div class="col">DELIVERY FEE</div>
                        <div class="col text-right">RM <%= dF%></div>
                    </div>
                    <%
                        }
                    %>

                    <div class="row" style="padding: 1vh 0;">
                        <div class="col">TOTAL PRICE</div>
                        <div class="col text-right">RM <%= session.getAttribute("finalTotal")%></div>
                    </div>

                    <form method="post" action="CheckOutReviewServlet">
                        <button class="submit" id="btn">COMFIRM PAYMENT</button>
                    </form>
                </div>
            </div>

        </div>
    </body>


</html>
