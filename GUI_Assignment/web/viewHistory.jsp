<%-- 
    Document   : viewHistory
    Created on : Apr 20, 2023, 10:52:03 PM
    Author     : Acer
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Orders> orderList = (ArrayList<Orders>) request.getAttribute("orderlist");
    ArrayList<Orderlist> oList = (ArrayList<Orderlist>) request.getAttribute("oList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View History</title>
    </head>
    <body>
        <h1>Order History</h1>
        <table>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Product ID</th>
                <th>Rate & Review</th>
            </tr>
            <% for (Orders o : orderList) {%>
            <% for (Orderlist ol : oList) {%>
            <% if (o.getOrdersId() == ol.getOrder().getOrdersId()) {%>
            <tr>
                <td><%= o.getOrdersId()%></td>
                <td><%= o.getOrdersDate()%></td>
                <td><%= o.getOrdersTtlPrice()%></td>
                <td><%= ol.getProduct().getProductId()%></td>
                <td><form action="ViewHistoryServlet" method="post">
                        <input type="hidden" name="productId" value="<%= ol.getProduct().getProductId()%>">
                        <input type="hidden" name="orderId" value="<%= o.getOrdersId()%>">
                        <button type="submit">Rate & Review</button>
                    </form>
                </td>

            </tr>
            <% }%>
            <% }%>
            <% }%>
        </table>
    </body>
</html>


