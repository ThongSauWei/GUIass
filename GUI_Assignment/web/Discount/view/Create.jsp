<%-- 
    Document   : Create
    Created on : Apr 26, 2023, 1:10:32 PM
    Author     : LOH XIN JIE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<%
    ArrayList<Product> plist = (ArrayList<Product>) request.getAttribute("plist");
    ArrayList<Product> invalidList = (ArrayList<Product>) request.getAttribute("invalidList");
    HashMap<String,String> errorList = (HashMap<String,String>) session.getAttribute("errorList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--add link for bootstrap, icon and -->
        <link rel="stylesheet" href="/GUI_Assignment/css/bootstrap.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="/GUI_Assignment/Discount/css/CreateStyle.css"/>
        <!--change title and favicon-->
        <title>${companyName}</title>
        <link rel="icon" href="/GUI_Assignment/Home/image/LEGOlogo.png" type="image/x-icon"/>
    </head>
    <body>
        <br><br>
        <%if(errorList != null && errorList.size() > 0){%>
            <div class="w-100 d-flex justify-content-center align-middle">
                <div class="alert alert-dismissible alert-danger w-75">
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    <h4>Error ! Unable To Create A New Discount</h4>
                    <ul>
                        <%for (String values : errorList.values()) {%>
                            <li><%=values%></li>
                        <%}%>
                    </ul>
                </div>
            </div>
        <%}%>
        <div class="mybox">
            <div>
                <form class="discountForm" method="post" action="/GUI_Assignment/DiscountCreateServlet">
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
                                    <option value="">Select Product</option>
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
                                <span class="text-danger dis-none" id="pdterror1"></span>
                                <span class="text-danger dis-none" id="pdterror2"></span>
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
                                <input type="number" name="percentage" class="form-control" max="100" min="1">
                                <span class="text-danger dis-none" id="percentageerror"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="text-end">
                                <a class="btn btn-danger w-25" href = '/GUI_Assignment/admin/view/home.jsp'><i class="bi bi-x-square"></i> &nbsp; CANCEL </a>
                                <button type="submit" class="btn btn-primary w-25" id="submitbtn"><i class="bi bi-pencil-square"></i> &nbsp; CREATE </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <%@include file="../../Home/view/Footer.jsp" %>
        <script>
            $(function(){
                $(".discountForm").change(function(){
                    $("#submitbtn").attr("disabled", false);
                    
                    <%if(invalidList != null && invalidList.size() > 0){%>
                        var pdtID = $("#pdtDiscount").val();
                        const list = [];
                        <%for(int i = 0; i < invalidList.size(); i++){%>
                            list[<%=i%>] = "<%=invalidList.get(i).getProductId()%>";
                        <%}%>
                        $("#pdterror1").addClass("dis-none");
                        $("#pdterror1").removeClass("dis-inline");
                        for(let i = 0; i < list.length; i++){
                            if(pdtID == list[i]){
                                //show error
                                $("#pdterror1").removeClass("dis-none");
                                $("#pdterror1").addClass("dis-inline");
                                $("#pdterror1").html("Product Have Already Be Discount, 1 Product Only Can Be Discount In 1 Times");
                                $("#submitbtn").attr("disabled", true);
                                break;
                            }
                        }
                    <%}%>
                    
                    $("#pdterror2").addClass("dis-none");
                    $("#pdterror2").removeClass("dis-inline");
                    
                    if($("#pdtDiscount").val() == ""){
                        $("#pdterror2").removeClass("dis-none");
                        $("#pdterror2").addClass("dis-inline");
                        $("#pdterror2").html("Product Cannot Be Empty");
                        $("#submitbtn").attr("disabled", true);
                    }
                    
                    //check discount size
                    $("#percentageerror").addClass("dis-none");
                    $("#percentageerror").removeClass("dis-inline");
                    var percentageSize = $("input[type=number]").val();
                    if(parseInt(percentageSize) > 100 || parseInt(percentageSize) < 1){
                        //show error
                        $("#percentageerror").removeClass("dis-none");
                        $("#percentageerror").addClass("dis-inline");
                        $("#percentageerror").html("The Max Size of Percentage is 100 & Min Size is 1");
                        $("#submitbtn").attr("disabled", true);
                    }
                });
            });
        </script>
    </body>
</html>
