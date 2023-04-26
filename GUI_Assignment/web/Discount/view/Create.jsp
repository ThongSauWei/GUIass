<%-- 
    Document   : Create
    Created on : Apr 26, 2023, 1:10:32 PM
    Author     : LOH XIN JIE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../Home/view/Header.jsp"%>
<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<%
    ArrayList<Product> plist = (ArrayList<Product>) request.getAttribute("plist");
    ArrayList<Product> invalidList = (ArrayList<Product>) request.getAttribute("invalidList");
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/GUI_Assignment/Discount/css/CreateStyle.css"/>
    </head>
    <body>
        <div class="mybox">
            <div>
                <form class="discountForm">
                    <table class="table table-borderless w-100">
                        <tr>
                            <td colspan="2" class="text-center">
                                <h3><i class="bi bi-tags-fill"></i> &nbsp; Create New Discount</h3>
                            </td>
                        </tr>
                        <tr>
                            <td class="form-label">
                                Product Discount : 
                            </td>
                            <td>
                                <select name="pdtDiscount" class="form-control" id="pdtDiscount">
                                    <option>Select Product</option>
                                    <%
                                        if(plist != null && plist.size() > 0){
                                            for(Product p : plist){
                                    %>
                                                <option value="<%=p.getProductId()%>"><%=p.getProductName()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                                <span class="text-danger dis-none" id="pdterror">Product Have Already Be Discount, 1 Product Only Can Be Discount In 1 Times</span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Discount Date : 
                            </td>
                        </tr>
                        <tr>
                            <td>FROM</td>
                            <td>
                                <input type="date" name="startDate" class="form-control" style="display: inline-block">
                            </td>
                        </tr>
                        <tr>
                            <td>TO</td>
                            <td>
                                <input type="date" name="endDate" class="form-control" style="display: inline-block">
                            </td>
                        </tr>
                        <tr>
                            <td class="w-25">
                                Discount Percentage : 
                            </td>
                            <td>
                                <input type="number" name="percentage" class="form-control" max="100" min="0">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="text-end">
                                <input type="submit" class="btn btn-primary" value="SUBMIT">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <%@include file="../../Home/view/Footer.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script>
            $(function(){
                $("select").change(function(){
                    <%int i = 0;%>
                    <%if(invalidList != null && invalidList.size() > 0){%>
                        var pdtID = $("#pdtDiscount").val();
                        $("#pdterror").addClass("dis-none");
                        $("#pdterror").removeClass("dis-inline");
                        $("input[type=submit]").attr("disabled", false);
                        for(let i = 0; i < <%=invalidList.size()%>; i++){
                            if(pdtID == <%=invalidList.get(i).getProductId()%>){
                                //show error
                                $("#pdterror").removeClass("dis-none");
                                $("#pdterror").addClass("dis-inline");
                                $("input[type=submit]").attr("disabled", true);
                                break;
                            }
                            <%i++;%>
                        }
                    <%}%>
                });
            });
        </script>
    </body>
</html>
