/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.ReportController;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utility.*;

/**
 *
 * @author Yeet
 */
public class ReportResult extends HttpServlet {

    protected void processReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportName = request.getParameter("reportName") != null ? request.getParameter("reportName") : "";
        String submit = request.getParameter("submit") != null ? request.getParameter("submit") : "";
        if (submit.equals("") || reportName.equals("")) {
            request.getSession().setAttribute("UnexceptableError", "some error");
            request.getSession().setAttribute("UnexceptableErrorDesc", "Unexpected Error");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
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
            String acs = request.getParameter("acs") != null ? request.getParameter("acs") : "";

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

            request.setAttribute("reportName", reportName);
            request.setAttribute("group", group);
            request.setAttribute("order", order);
            request.setAttribute("contorl", contorl);
            request.setAttribute("rows", rows);
            request.setAttribute("conditions", conditions);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/view/report_result.jsp");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("UnexceptableError", "report error");
            request.getSession().setAttribute("UnexceptableErrorDesc", "Unexpected Error");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
            return;
        }

        if (contorl == null) {
            request.getSession().setAttribute("UnexceptableError", "report error");
            request.getSession().setAttribute("UnexceptableErrorDesc", "Unexpected Error");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (CheckPermission.permissionStaff(request)) {
            processReport(request, response);
        } else if (CheckPermission.permissionNoLogin(request)) {
            response.sendRedirect("/GUI_Assignment/login/staffLogin.jsp");
        } else {
            //turn to error page , reason - premission denied
            response.sendRedirect("/GUI_Assignment/Home/view/PermissionDenied.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (CheckPermission.permissionStaff(request)) {
            processReport(request, response);
        } else if (CheckPermission.permissionNoLogin(request)) {
            response.sendRedirect("/GUI_Assignment/login/staffLogin.jsp");
        } else {
            //turn to error page , reason - premission denied
            response.sendRedirect("/GUI_Assignment/Home/view/PermissionDenied.jsp");
        }
    }

}
