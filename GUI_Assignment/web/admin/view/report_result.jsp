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
    String reportName = (String) request.getAttribute("reportName");
    String group = (String) request.getAttribute("group");
    String order = (String) request.getAttribute("order");
    ReportController contorl = (ReportController) request.getAttribute("contorl");
    List<HashMap<String, Object>> rows = (List<HashMap<String, Object>>) request.getAttribute("rows");
    String[] conditions = (String[]) request.getAttribute("conditions");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--change title and favicon-->
        <title>${companyName}</title>
        <link rel="icon" href="/GUI_Assignment/Home/image/LEGOlogo.png" type="image/x-icon"/>
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
            <h4 class="text-center mt-3 mb-3"><%=group + (order.equals("") == false ? "; " + order : "")%></h4>
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
        <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
