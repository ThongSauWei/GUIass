<%@page import="java.util.ArrayList"%>
<%@page import="Controller.ReportController"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : report_result
    Created on : Mar 23, 2023, 1:44:49 PM
    Author     : Yeet
--%>

<%
    String reportName = request.getParameter("reportName") != null ? request.getParameter("reportName") : "";
    if (reportName.equals("")) {
        //response.sendRedirect("unexpected_error.jsp");
        //return;
        ;
    }
    String query = "SELECT\n"
            + "  p.product_id,\n"
            + "  p.product_name,\n"
            + "  SUM(ol.orders_quantity) AS total_units_sold,\n"
            + "  SUM(ol.orders_subprice) AS total_revenue\n"
            + "FROM\n"
            + "  orders o\n"
            + "  JOIN orderlist ol ON o.orders_id = ol.orders_id\n"
            + "  JOIN product p ON ol.product_id = p.product_id\n"
            + "WHERE\n"
            + "  o.orders_date BETWEEN '2022-01-01' AND '2022-12-31'\n"
            + "GROUP BY\n"
            + "  p.product_id,\n"
            + "  p.product_name\n"
            + "ORDER BY\n"
            + "  total_revenue DESC\n"
            + "";

    List<HashMap<String, Object>> result = ReportController.salesReport();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= reportName%></title>
        <link rel="stylesheet" href="https://bootswatch.com/5/darkly/bootstrap.min.css">

        
        <style>
            .fixed-bottom-center {
                position: fixed;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center my-5">Sales Report</h1>
            <div class="col-md-12">
                <table class="table table-hover table-striped">
                    <thead class="bg-primary ">
                        <tr>
                            <%
                                List<String> columns = ReportController.getColumnNames(query);
                                for (String header : columns) {
                                    out.print("<th class=''>" + header + "</th>");
                                }
                            %>
                        </tr>
                    </thead>
                    <tbody>
                        <%List<HashMap<String, Object>> rows = ReportController.salesReport();%>
                        <%for (HashMap<String, Object> row : rows) {%><tr>
                            <%
                                for (String column : columns) {%>
                            <td><%=row.get(column.replace(" ", "_").toUpperCase())%></td>
                            <%}%>


                        </tr>
                        <%}%>
                        <tr>
                        </tr>
                    </tbody>
                </table>
            </div>
            <table class="table table-striped w-25" hidden>
                <thead class="thead-dark">
                    <tr>
                        <th colspan="2">Conditions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1002</td>
                        <td>1002</td>
                    </tr>
                    <tr>
                        <td>1003</td>
                        <td>1002</td>
                    </tr>
                    <tr>
                        <td>1004</td>
                        <td>1002</td>
                    </tr>
                    <tr>
                        <td>1005</td>
                        <td>1002</td>
                    </tr>
                    <tr>
                        <td>1006</td>
                        <td>1002</td>
                    </tr>
                </tbody>
            </table>

        </div>
                        
                        
            <a href="report.jsp" class=" mb-1 btn btn-danger fixed-bottom-center  rounded-pill"><strong>X</strong></a>
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
