package Servlet;

import Controller.StaffController;
import Model.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yeet
 */
public class StaffList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String search = request.getParameter("search") == null ? "" : request.getParameter("search");
            String delete = request.getParameter("delete");
            ArrayList<Staff> staffs = new StaffController().getStaff(search);
            if (staffs == null) {
                response.sendRedirect("unexpected_error.jsp");
            } else if (staffs.isEmpty()) {
                out.print("<td colspan=8>No Record.</td>");
            }
            for (Staff staff : staffs) {
                out.println("<tr>");
                out.println("<td>" + staff.getStaffId() + "</td>");
                out.println(" <td>" + staff.getStaffName() + "</td>");
                out.println("<td>" + staff.getStaffPhNo() + "</td>");
                out.println("<td>" + staff.getStaffEmail() + "</td>");
                out.println(" <td>" + staff.getDisplayFormatBirthdate() + "</td>");
                out.println("<td>" + staff.getStaffIc() + "</td>");
                out.println(" <td>" + staff.getStaffPass() + "</td>");
                out.println("  <td><a href=\"../../StaffMaint?id=" + staff.getStaffId() + "&isNew=false\" style=\"font-size:20px;color:grey\" class=\"fa\"><i class=\"edit fa fa-pencil\"></i></a></td>");
                out.println("</tr>");
            }
        } catch (SQLException ex) {
            response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
