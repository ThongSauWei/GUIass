package Servlet;

/**
 * @author LOH XIN JIE
 */
import javax.servlet.http.*;
import javax.servlet.*;
import Model.*;
import DataAccess.*;
import DataAccess.Mapper.DiscountMapper;
import DataAccess.Mapper.ProductMapper;
import java.io.*;
import java.util.*;
import Utility.*;
import java.text.ParseException;
import java.sql.SQLException;

public class DiscountCreateServlet extends HttpServlet {

    DBTable db = new DBTable();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("errorList");
        //error map
        HashMap<String, String> errorMap = new HashMap<>();
        //post for form
        try {
            //get data
            String productID = request.getParameter("pdtDiscount");
            Date startDate = Converter.convertStringToUtilDate(request.getParameter("startDate"));
            Date endDate = Converter.convertStringToUtilDate(request.getParameter("endDate"));
            String percentage = request.getParameter("percentage");
            Discount d = new Discount();

            //check product id
            if (productID != null && !productID.trim().isEmpty()) {
                int id = Integer.parseInt(productID);
                d.setProduct(new Product(id));
                //get unavaliable list
                ArrayList<Product> ulist = getUnableProduct();
                if (ulist != null && ulist.size() > 0) {
                    for (Product p : ulist) {
                        if (p.getProductId() == id) {
                            errorMap.put("productIdError", "Product Have Already Be Discount, 1 Product Only Can Be Discount In 1 Times");
                        }
                    }
                }
            } else {
                errorMap.put("productIdError", "Choice cannot be empty");
            }

            //date cannot smaller than 1900 year
            if (startDate.getYear() + 1900 < 1900) {
                errorMap.put("startDateError", "Invalid Date Format, Year must Be Bigger Than 1900");
            } else {
                d.setDiscountStartDate(startDate);
            }

            if (endDate.getYear() + 1900 < 1900) {
                errorMap.put("endDateError", "Invalid Date Format, Year must Be Bigger Than 1900");
            } else {
                d.setDiscountEndDate(endDate);
            }

            if (endDate.before(startDate)) {
                errorMap.put("logicDateError", "End Date Cannot Smaller Than Start Date");
            }

            if (percentage != null && !percentage.trim().isEmpty()) {
                int percentages = Integer.parseInt(request.getParameter("percentage"));
                d.setDiscountPercentage(percentages);
                if (percentages > 100 || percentages < 1) {
                    errorMap.put("percentageError", "The Max Size of Percentage is 100 & Min Size is 1");
                }
            } else {
                errorMap.put("percentageError", "Percentage Cannot Be Empty");
            }

            if (errorMap.size() > 0) {
                //back to page
                request.getSession().setAttribute("errorList", errorMap);
                response.sendRedirect("DiscountCreateServlet");
            } else {
                //save data no error
                db.Discount.Add(new DiscountMapper(), d);
                request.getSession().setAttribute("successMessage", "Create Successfull");
                response.sendRedirect("/GUI_Assignment/index.jsp");
            }

        } catch (ParseException ex) {
            //catch set into hashmap
            //date cannot be empty
            errorMap.put("logicDateError", "Start Date and End Date Cannot be empty");
            request.getSession().setAttribute("errorList", errorMap);
            response.sendRedirect("DiscountCreateServlet");
        } catch (SQLException ex) {
            //turn error page
            request.getSession().setAttribute("UnexceptableError", ex.getMessage());
            request.getSession().setAttribute("UnexceptableErrorDesc", "Database Server Exception");
            request.getRequestDispatcher("Home/view/ErrorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //get dropdown list
            String plistQuery = "SELECT * FROM PRODUCT WHERE PRODUCT_ACTIVE = ?";
            ArrayList<Object> condition = new ArrayList<>();
            condition.add(new Character('1'));

            ArrayList<Product> plist = db.Product.getData(new ProductMapper(), condition, plistQuery);

            ArrayList<Product> invalidList = getUnableProduct();

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

    protected ArrayList<Product> getUnableProduct() throws SQLException {
        //get unavaliable list
        String ulistQuery = "SELECT * "
                + "FROM DISCOUNT "
                + "INNER JOIN PRODUCT ON DISCOUNT.PRODUCT_ID = PRODUCT.PRODUCT_ID "
                + "WHERE DISCOUNT.DISCOUNT_START_DATE <= ? AND DISCOUNT.DISCOUNT_END_DATE >= ?";
        ArrayList<Object> condition = new ArrayList<>();
        Date currDate = new Date();
        condition.add(currDate);
        condition.add(currDate);

        return db.Product.getData(new ProductMapper(), condition, ulistQuery);
    }

    @Override
    public void init() throws ServletException {
        this.db = new DBTable();
    }
}
