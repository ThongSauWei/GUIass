package Servlet;

import Controller.StaffController;
import Model.Staff;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yeet
 */
public class StaffMaint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String delete = request.getParameter("delete");

        if (delete != null && id != null) {
            try {
                if (new StaffController().dltStaff(Integer.parseInt(id))) {
                    response.sendRedirect("/GUI_Assignment/admin/view/staff_list.jsp?delete=1");
                    return;
                } else {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                    return;
                }
            } catch (SQLException ex) {
                response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
            }
        }

        boolean isNew = request.getParameter("isNew").equals("true");
        if (!isNew) {
            try {
                ArrayList<Staff> staffs = new StaffController().getStaff(id);
                if (!staffs.isEmpty()) {
                    Staff staff;
                    staff = staffs.get(0);
                    HttpSession session = request.getSession();
                    session.setAttribute("staff", staff);
                    response.sendRedirect("/GUI_Assignment/admin/view/staff_maint.jsp?id=" + staff.getStaffId() + "&isNew=false");
                } else {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }
            } catch (SQLException ex) {
                response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        int action = request.getParameter("action") == null ? 0 : Integer.parseInt(request.getParameter("action"));
        if (request.getParameter("submit") != null) {
            int submit = Integer.parseInt(request.getParameter("submit"));
            if (submit == 1) {
                try {
                    String name = request.getParameter("name");
                    String phoneNum = request.getParameter("phoneNum");
                    String email = request.getParameter("email");
                    String birthday = request.getParameter("birthday");
                    String ic = request.getParameter("ic");
                    String password = request.getParameter("password");
                    action = Integer.parseInt(request.getParameter("action"));
                    if (new StaffController().addStaff(name, password, ic, phoneNum, email, birthday)) {
                        if (action == 1) {
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_list.jsp");
                            return;
                        }
                        if (action == 2) {
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_maint.jsp?isNew=true&action=" + action + "");
                            return;
                        }
                        if (action == 3) {
                            ArrayList<Staff> staffs = new StaffController().getStaff(id);
                            if (!staffs.isEmpty()) {
                                Staff staff;
                                staff = staffs.get(0);
                                HttpSession session = request.getSession();
                                session.setAttribute("staff", staff);
                            }
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_maint.jsp?isNew=false&action=" + action + "&isSaved=true&id=" + new StaffController().getLatestStaff().getStaffId() + "");
                        }
                    } else {
                        response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                    }
                } catch (Exception ex) {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }

            } else if (submit == 0) {

                try {
                    String name = request.getParameter("name");
                    String phoneNum = request.getParameter("phoneNum");
                    String email = request.getParameter("email");
                    String birthday = request.getParameter("birthday");
                    String ic = request.getParameter("ic");
                    String password = request.getParameter("password");
                    action = Integer.parseInt(request.getParameter("action"));

                    if (new StaffController().updateStaff(id, name, password, ic, phoneNum, email, birthday)) {
                        if (action == 1) {
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_list.jsp");
                            return;
                        }
                        if (action == 2) {
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_maint.jsp?isNew=true&action=" + action + "");
                            return;
                        }
                        if (action == 3) {
                            ArrayList<Staff> staffs = new StaffController().getStaff(id);
                            if (!staffs.isEmpty()) {
                                Staff staff;
                                staff = staffs.get(0);
                                HttpSession session = request.getSession();
                                session.setAttribute("staff", staff);
                            }
                            response.sendRedirect("/GUI_Assignment/admin/view/staff_maint.jsp?isNew=false&action=" + action + "&isSaved=true&id=" + id + "");
                        }
                    } else {
                        response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                    }
                } catch (Exception ex) {
                    response.sendRedirect("/GUI_Assignment/admin/view/unexpected_error.jsp");
                }

            }

        }
    }
}
