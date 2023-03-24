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
    String submit = request.getParameter("submit") != null ? request.getParameter("submit") : "";
    if (submit.equals("") || reportName.equals("")) {
        response.sendRedirect("unexpected_error.jsp");
        return;
    }
    String[] conditions = null;
    ReportController contorl = null;
    List<HashMap<String, Object>> rows = null;
    String group = null;
    String order = null;
    if (submit.equals("1")) {//sales
        String column = request.getParameter("column") != null ? request.getParameter("column") : "";
        String dateFrom = request.getParameter("dateFrom") != null ? request.getParameter("dateFrom") : "";
        String dateTo = request.getParameter("dateTo") != null ? request.getParameter("dateTo") : "";
        String groupby = request.getParameter("groupby") != null ? request.getParameter("groupby") : "";
        String orderby = request.getParameter("orderby") != null ? request.getParameter("orderby") : "";
        String status = request.getParameter("status") != null ? request.getParameter("status") : "";
        String acs = request.getParameter("acs") != null? request.getParameter("acs") : "";

        conditions = new String[6];
        conditions[0] = "Date From";
        conditions[1] = dateFrom;
        conditions[2] = "Date To";
        conditions[3] = dateTo;
        conditions[4] = "Status";
        conditions[5] = status.equals("1") == true ? "Active" : (status.equals("0") == true ? "Inactive" : "All");

        contorl = new ReportController(1, column, dateFrom, dateTo, groupby, orderby, status, acs);
        
        group = contorl.getGroup();
        group = ReportController.salesFormatDisplay(contorl.getGroup());
        
        order = ReportController.salesFormatDisplay(contorl.getOrder());
        
        rows = contorl.salesReport();
    } else {
        response.sendRedirect("unexpected_error.jsp");
        return;
    }

    if (contorl == null) {
        response.sendRedirect("unexpected_error.jsp");
        return;
    }
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
            @media print{
                #back-btn{
                   display: none;
                }
                *{
                 color: black !important;    
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center mt-5"><%= reportName%> Report</h1>
            <h4 class="text-center mt-3 mb-3"><%=group + (order.equals("") == false ? "; "+order : "")%></h4>
            <div class="col-md-12">

                <table class="table table-hover table-striped">
                    <thead class="bg-primary ">
                        <tr>
                            <%
                                List<String> columns = contorl.getDynamicColumnNames();
                                for (String header : columns) {
                                    out.print("<th class=''>" + header + "</th>");
                                }
                            %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (rows.size() > 0) {
                                for (HashMap<String, Object> row : rows) {%><tr>
                            <%
                                for (String column : columns) {%>
                            <td><%=row.get(column.replace(" ", "_").toUpperCase())%></td>
                            <%}%>


                        </tr>
                        <%}
                            } else {
                                out.print("<td colspan='" + contorl.getDynamicColumnNames().size() + "'>No record</td>");
                            }%>
                        <tr>
                        </tr>
                    </tbody>
                </table>

            </div>
            <table class="" style="font-size: 10px;">
                <thead class="thead-dark">
                    <tr>
                        <th colspan="2">Conditions</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (int i = 0; i < conditions.length - 1; i += 2) {%>
                    <tr>
                        <th><%=conditions[i]%></th>
                        <td>&nbsp;:&nbsp;</td>
                        <td><%=conditions[i + 1]%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>


        <a id="back-btn" onclick="history.back()" class=" mb-1 btn btn-danger fixed-bottom-center  rounded-pill"><strong>X</strong></a>
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
