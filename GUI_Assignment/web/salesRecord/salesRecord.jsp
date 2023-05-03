<%-- 
    Document   : salesRecord
    Created on : Apr 28, 2023, 2:56:23 AM
    Author     : erika
--%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="Controller.OrderListingController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!--change title and favicon-->
        <title>${companyName}</title>
        <link rel="icon" href="/GUI_Assignment/Home/image/LEGOlogo.png" type="image/x-icon"/>
    </head>
    <body>
        <h1>Sales Record</h1>
        <form method="POST" action="${pageContext.request.contextPath}/salesRecord">
<!--            <input type="text" name="search" value="${param.search}">
            <input type="submit" value="Search">
        </form>-->
        <br>
        <table border="1">
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Member ID</th>
                <th>Member Name</th>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Payment Type</th>
                <th>Total Price</th>
                <th>Tax</th>
                <th>Delivery Fee</th>
                <th>Express Shipping</th>
            </tr>
            <c:forEach items="${salesRecords}" var="record">
                <tr>
                    <td>${record['ORDERS_ID']}</td>
                    <td>${record['ORDERS_DATE']}</td>
                    <td>${record['MEMBER_ID']}</td>
                    <td>${record['MEMBER_NAME']}</td>
                    <td>${record['PRODUCT_ID']}</td>
                    <td>${record['PRODUCT_NAME']}</td>
                    <td>${record['ORDERS_QUANTITY']}</td>
                    <td>${record['ORDERS_PAYMENT_TYPE']}</td>
                    <td>${record['ORDERS_TTLPRICE']}</td>
                    <td>${record['ORDERS_TAX']}</td>
                    <td>${record['ORDERS_DELIVERY_FEE']}</td>
                    <td>${record['ORDERS_EXPRESS_SHIPPING']}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>