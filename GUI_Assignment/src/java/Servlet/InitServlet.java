package Servlet;

/**
 * @author LOH XIN JIE
 */
import javax.servlet.http.*;
import javax.servlet.*;

public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        String companyName = "Company Name is Missing";
        String adminEmail = "Admin Email is Missing";
        ServletContext context = getServletContext();
        if (context.getInitParameter("CompanyName") != null) {
            companyName = context.getInitParameter("CompanyName");
        }
        if (context.getInitParameter("AdminEmail") != null) {
            adminEmail = context.getInitParameter("AdminEmail");
        }
        context.setAttribute("companyName", companyName);
        context.setAttribute("adminEmail", adminEmail);
    }
}
