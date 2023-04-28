package Servlet;

/**
 * @author LOH XIN JIE
 */
import javax.servlet.*;
import javax.servlet.http.*;
import DataAccess.*;
import DataAccess.Mapper.DiscountMapper;
import DataAccess.Mapper.ProductMapper;
import java.io.*;
import java.sql.SQLException;
import Model.*;

public class DiscountDeleteServlet extends HttpServlet {

    private DBTable db = new DBTable();

    @Override
    public void init() throws ServletException {
        db = new DBTable();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int discountID = Integer.parseInt(request.getParameter("discountID"));

            //get data and display at front
            Discount discount = db.Discount.getData(new DiscountMapper(), discountID).get(0);

            Product product = db.Product.getData(new ProductMapper(), discount.getProduct().getProductId()).get(0);

            discount.setProduct(product);

            request.setAttribute("discount", discount);
            request.getRequestDispatcher("Discount/view/Delete.jsp").forward(request, response);
        } catch (SQLException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Discount ID Missing Problem");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("errorList");
        request.getSession().removeAttribute("DiscountSuccess");
        try {
            int discountID = Integer.parseInt(request.getParameter("discountID"));

            Discount discount = db.Discount.getData(new DiscountMapper(), discountID).get(0);

            //delete
            db.Discount.Delete(new DiscountMapper(), discount);

            request.getSession().setAttribute("DiscountSuccess", "Delete Successful");
            request.getRequestDispatcher("/DiscountDisplayServlet").forward(request, response);
        } catch (SQLException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Discount ID Missing Problem");
            request.getRequestDispatcher("admin/view/unexpected_error.jsp").forward(request, response);
        }
    }
}
