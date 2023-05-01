<%-- 
    Document   : index
    Created on : Mar 17, 2023, 6:44:23 PM
    Author     : guoc7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.test"%>
<%@page import="Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Product"%>
<%@include file="/Home/view/Header.jsp"%>
<!DOCTYPE html>
<html>
    <head>
     <title>Lego</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
        <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-element-bundle.min.js"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8">
        <link href="css/productcontent.css" rel="stylesheet" type="text/css"/>
        <link href="css/menulist.css" rel="stylesheet" type="text/css"/>
        <link href="css/itemcart.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%@ include file="/productMenuServlet" %>
        <%--<%@ include file="WEB-INF/productcontent.jsp" %>--%>
        <%--<%@ include file="WEB-INF/itemcart.jsp" %>--%>

    </body>
</html>
