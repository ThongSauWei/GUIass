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
<html class="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="salesRecord.css" rel="stylesheet" />
        <link href="/GUI_Assignment/css/bootstrap.css" rel="stylesheet" />
        <title>Sales Record Main Page</title>
    </head>

    <body>
        <form action="/GUI_Assignment/salesRecord" method="POST">
            <div class="container">
                <h2>Sales Record</h2>
                <form method="get" action="/GUI_Assignment/salesRecord">
                    <table class="table table-borderless">
                        <tr>
                            <td>Product ID </td>
                            <td><input type="hidden" name="productID" value="1234" readonly></td>
                            <td>Member ID </td>
                            <td><input type="number" class="form-control" name="memberID" value="<%=request.getParameter("memberID")%>"></td>
                        </tr>
                        <tr>
                            <td>City </td>
                            <td><input type="text" name="city" class="form-control" value="<%=request.getParameter("city") == null ? "" : request.getParameter("city")%>"></td>
                            <td>Postcode </td>
                            <td><input type="text" name="postcode" class="form-control" value="<%=request.getParameter("postcode") == null ? "" : request.getParameter("postcode")%>"></td>
                        </tr>
                        <tr>
                            <td>State </td>
                            <td><input type="text" name="state" class="form-control" value="<%=request.getParameter("state") == null ? "" : request.getParameter("state")%>"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr class="text-end">
                            <td colspan="4">
                                <a class="btn btn-secondary mx-2" href="/GUI_Assignment/salesRecord">Clear Search</a>
                                <input type="submit" value="Search" class="btn btn-primary">
                            </td>
                        </tr>
                    </table>
                </form>
                <ul class="responsive-table">
                    <li class="table-header">
                        <div class="col col-1">Product Name</div>
                        <div class="col col-2">Member List</div>
                        <div class="col col-3">Quantity</div>
                        <div class="col col-4">Subprice</div>
                        <div class="col col-5">Mem Address</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="productID">42235</div>
                        <div class="col col-2" data-label="productName">John Doe</div>
                        <div class="col col-3" data-label="quantity">1</div>
                        <div class="col col-4" data-label="subprice">20</div>
                        <div class="col col-5" data-label="MemAddress">Member Address</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="productID">42235</div>
                        <div class="col col-2" data-label="productName">John Doe</div>
                        <div class="col col-3" data-label="quantity">2</div>
                        <div class="col col-4" data-label="subprice">20</div>
                        <div class="col col-5" data-label="MemAddress">Member Address</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="productID">42235</div>
                        <div class="col col-2" data-label="productName">John Doe</div>
                        <div class="col col-3" data-label="quantity">3</div>
                        <div class="col col-4" data-label="subprice">20</div>
                        <div class="col col-5" data-label="MemAddress">Member Address</div>
                    </li>
                    <li class="table-row">
                        <div class="col col-1" data-label="productID">42235</div>
                        <div class="col col-2" data-label="productName">John Doe</div>
                        <div class="col col-3" data-label="quantity">1</div>
                        <div class="col col-4" data-label="subprice">20</div>
                        <div class="col col-5" data-label="MemAddress">Member Address</div>
                    </li>
                </ul>
            </div>
        </form>
    </body>
</html>