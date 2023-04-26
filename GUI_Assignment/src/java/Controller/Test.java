package Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<String, String>();

        String name = request.getParameter("name");
        if(name == null || name.isEmpty()) {
            errors.put("name", "Name is required");
        }

        String email = request.getParameter("email");
        if(email == null || email.isEmpty()) {
            errors.put("email", "Email is required");
        }

        if(!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/new.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("new.jsp");
        }
    }
}
