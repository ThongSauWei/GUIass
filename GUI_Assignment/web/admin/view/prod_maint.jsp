<%@page import="java.util.ArrayList"%>
<%@page import="Model.Product"%>
<%@page import="Controller.prodController"%>
<%
    Product product = null;

    int action = request.getParameter("action") == null ? 0 : Integer.parseInt(request.getParameter("action"));
    if (request.getParameter("submit") != null) {
        int submit = Integer.parseInt(request.getParameter("submit"));
        if (submit == 1) {
            String name = request.getParameter("name");
            String desc = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            char active = request.getParameter("active").charAt(0);

            if (new prodController().addProd(name, desc, price, active)) {
                if (action == 1) {
                    response.sendRedirect("prod_list.jsp");
                    return;
                }
                if (action == 2) {
                    response.sendRedirect("prod_maint.jsp?isNew=true&action=" + action + "");
                    return;
                }
                if (action == 3) {
                    response.sendRedirect("prod_maint.jsp?isNew=false&action=" + action + "&isSaved=true&id=" + new prodController().getLatestProd().getProductId() + "");
                    return;
                }
            } else {
                response.sendRedirect("unexpected_error.jsp");
                return;
            }
        } else if (submit == 0) {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String desc = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            char active = request.getParameter("active") != null ? request.getParameter("active").charAt(0) : '0';

            if (new prodController().updateProd(id, name, desc, price, active)) {
                if (action == 1) {
                    response.sendRedirect("prod_list.jsp");
                    return;
                }
                if (action == 2) {
                    response.sendRedirect("prod_maint.jsp?isNew=true&action=" + action + "");
                    return;
                }
                if (action == 3) {
                    response.sendRedirect("prod_maint.jsp?isNew=false&&action=" + action + "&isSaved=true&id=" + id + "");
                    return;
                }
            } else {
                response.sendRedirect("unexpected_error.jsp");
                return;
            }
        }
    }

    boolean isNew = request.getParameter("isNew").equals("true") ? true : false;
    if (!isNew) {
        String id = request.getParameter("id");
        product = new prodController().getProd(id);

        if (product == null) {
            response.sendRedirect("unexpected_error.jsp");
            return;
        }
    }

    boolean isSaved = request.getParameter("isSaved") != null ? Boolean.parseBoolean(request.getParameter("isSaved")) : false;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Maintaint Page</title>
        <link rel="stylesheet" href="https://bootswatch.com/5/darkly/bootstrap.min.css">
        <style>
            .form_wid{
                max-width: 850px;
            }
            .error-message {
                color: red;
                font-size:12px;
            }
            .table td{
                width:50%;
                min-width:400px;
            }

        </style>
    </head>
    <body>
        <div class="form_wid container mt-5">
            <h2 class="text-center mb-3"><%= isNew ? "Add New" : "Edit"%> Product</h2>
            <form action="prod_maint.jsp" onsubmit="return validateForm()">
                <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Field</th>
                            <th scope="col">Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">ID</th>
                            <td><input readonly style="color:grey;" type="text" id="id" name="id" class="form-control" placeholder="ID will be auto generated." value="<%=isNew ? "" : product.getProductId()%>"></td>
                        </tr>
                        <tr>
                            <th scope="row">Name</th>
                            <td><span id="name_error" class="error-message"></span><input onblur="isInputFieldEmpty(this);" type="text" id="name" name="name" class="error-border form-control" value="<%=isNew ? "" : product.getProductName()%>"></td>
                        </tr>
                        <tr>
                            <th scope="row">Description</th>
                            <td><textarea id="description" name="description" class="error-border form-control" style="height: 180px;"><%=isNew ? "" : product.getProductDesc()%></textarea></td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td><span id="price_error" class="error-message"></span><input onkeypress="return isPriceKey(event)" onchange="formatPrice(this)" onblur="isValidPrice()" type="text" id="price" name="price" class="error-border form-control" value="<%=isNew ? "" : product.getProductPrice()%>"></td>
                        </tr>
                        <tr>
                            <th scope="row">Active</th>
                            <td><input value = "1" type="checkbox" id="active" name="active" class="form-check-input" <%=isNew ? "" : product.getProductActive() == '1' ? "checked" : ""%>></td>
                        </tr>
                    </tbody>
                </table>
                <div class="d-flex justify-content-end">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio1" value="1" checked>
                        <label class="form-check-label" for="inlineRadio1">Return to listing page</label>
                    </div>
                    <% if (isNew) {%>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio2" value="2" <%=action == 2 ? "checked" : ""%>>
                        <label class="form-check-label" for="inlineRadio2">Insert another record</label>
                    </div>
                    <%}%>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio3" value="3" <%=action == 3 ? "checked" : ""%>>
                        <label class="form-check-label" for="inlineRadio3">Preview record</label>
                    </div>
                    <input hidden name="submit" value="<%=isNew ? "1" : "0"%>">
                    <button type="button" onclick="cancel()" class="btn btn-danger ms-3">Cancel</button>
                    <button type="reset" onclick="resets()" class="btn btn-secondary ms-3">Reset</button>
                    <button type="submit" class="btn btn-primary ms-3">Submit</button>
                </div>
            </form>
        </div>
    </body>
    <%if (isSaved) {%><script>alert('Record Saved.');</script> <%}%>
    <script src="../js/maint_page_util.js" type="text/javascript"></script>
    <script src="../js/maint_page_prod.js" type="text/javascript"></script>
</html>
