package Servlet;

/**
 * @author LOH XIN JIE
 */
import javax.servlet.http.*;
import javax.servlet.*;
import Model.*;
import DataAccess.*;
import DataAccess.Mapper.ProductMapper;
import java.io.*;
import java.util.*;
import Utility.*;
import java.text.ParseException;
import java.sql.SQLException;

public class DiscountCreateServlet extends HttpServlet {

    DBTable db = new DBTable();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //get data
            int productID = Integer.parseInt(request.getParameter("pdfDiscount"));
            Date startDate = Converter.convertStringToUtilDate(request.getParameter("star"));
            //check validate
            String sqlQuery = "SELECT * FROM Product WHERE PRODUCT_ID = ? AND PRODUCT_ACTIVE = ?";
            ArrayList<Object> condition = new ArrayList<>();
            condition.add(new Integer(productID));
        } catch (ParseException ex) {
            //catch
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //post for form
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //get dropdown list
            String plistQuery = "SELECT * FROM PRODUCT WHERE PRODUCT_ACTIVE = ?";
            ArrayList<Object> condition = new ArrayList<>();
            condition.add(new Integer(1));

            ArrayList<Product> plist = db.Product.getData(new ProductMapper(), condition, plistQuery);

            //get unavaliable list
            String ulistQuery = "SELECT * "
                    + "FROM DISCOUNT "
                    + "INNER JOIN PRODUCT ON DISCOUNT.PRODUCT_ID = PRODUCT.PRODUCT_ID "
                    + "WHERE DISCOUNT.DISCOUNT_START_DATE <= ? AND DISCOUNT.DISCOUNT_END_DATE >= ?";
            condition.clear();
            Date currDate = new Date();
            condition.add(currDate);
            condition.add(currDate);

            ArrayList<Product> invalidList = db.Product.getData(new ProductMapper(), condition, ulistQuery);

            //error map
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("productIdError", "Product Have Already Be Discount, 1 Product Only Can Be Discount In 1 Times");

            request.setAttribute("errorList", errorMap);
            request.setAttribute("plist", plist);
            request.setAttribute("invalidList", invalidList);
            request.getRequestDispatcher("Discount/view/Create.jsp").forward(request, response);
        } catch (SQLException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            request.getRequestDispatcher("Home/view/ErrorPage.jsp").forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        this.db = new DBTable();
    }
}
