package Controller;

/**
 * @author LOH XIN JIE
 */
import Model.*;
import DataAccess.*;
import DataAccess.Mapper.DiscountMapper;
import java.sql.SQLException;
import java.util.*;

/*One product will only have one discount in time*/
public class DiscountController {

    //if have discount will return
    public static Discount getDiscount(DBTable db, int productId) throws SQLException {
        String sqlQuery = "SELECT * FROM DISCOUNT "
                + "WHERE PRODUCT_ID = ? AND "
                + "DISCOUNT_START_DATE <= ? AND "
                + "DISCOUNT_END_DATE >= ?";

        Date currdate = new Date();

        ArrayList<Object> condition = new ArrayList<>();
        condition.add(new Integer(productId));
        condition.add(currdate);
        condition.add(currdate);

        ArrayList<Discount> dlist = db.Discount.getData(new DiscountMapper(), condition, sqlQuery);

        return dlist.size() > 0 ? dlist.get(0) : null;
    }

    public static double getPrice(double productPrice, int discountPercentage) {
        return Math.round((productPrice * (1.0 - (discountPercentage * 1.0 / 100))) * 10) / 10.0;
    }
}