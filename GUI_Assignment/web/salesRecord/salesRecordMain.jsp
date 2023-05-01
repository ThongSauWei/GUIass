<%-- 
    Document   : salesRecordMain
    Created on : May 1, 2023, 12:59:33 AM
    Author     : erika
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/GUI_Assignment/salesRecord/salesRecordMain.css" rel="stylesheet" />
        <title>Sales Record Main Page</title>
    </head>
    <body>
        <form action="/GUI_Assignment/salesRecordMain" method="GET">
            <div class="container">
                <h2>Sales Record Main Page</h2>
                <ul class="responsive-table">
                    <li class="table-header">
                        <div class="col col-1">Product ID</div>
                        <div class="col col-2">Product Name</div>
                        <div class="col col-4">View</div>
                    </li>
                </ul>
            </div>
        </form>
    </body>
</html>
