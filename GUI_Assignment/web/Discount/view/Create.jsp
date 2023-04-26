<%-- 
    Document   : Create
    Created on : Apr 26, 2023, 1:10:32 PM
    Author     : LOH XIN JIE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../Home/view/Header.jsp"%>
<!DOCTYPE html>
<html>
    <body>
        <form class="p-5 w-50">
            <table class="table table-borderless w-100">
                <tr>
                    <td class="w-25">
                        Product Discount : 
                    </td>
                    <td>
                        <select name="pdtDiscount" class="form-control w-100">
                            <option value="first">text1</option>
                            <option value="second">text2</option>
                            <option value="third">text3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        Discount Date : 
                    </td>
                </tr>
                <tr>
                    <td class="w-25">FROM</td>
                    <td>
                        <input type="date" name="startDate" class="form-control w-75" style="display: inline-block">
                    </td>
                </tr>
                <tr>
                    <td class="w-25">TO</td>
                    <td>
                        <input type="date" name="endDate" class="form-control w-75" style="display: inline-block">
                    </td>
                </tr>
                <tr>
                    <td class="w-25">
                        Discount Percentage : 
                    </td>
                    <td>
                        <input type="number" name="percentage" class="form-control w-75" maxlength="100">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="text-end">
                        <input type="submit" class="btn btn-primary" value="SUBMIT">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
