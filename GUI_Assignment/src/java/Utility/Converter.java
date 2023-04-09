package Utility;

/**
 * @author LOH XIN JIE
 */
import java.text.SimpleDateFormat;

public class Converter {

    public static java.util.Date convertSQLDateToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date convertUtilDateToSQLDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static String convertToString(Object obj) {
        return String.valueOf(obj);
    }

    //example : 20230408161001529
    public static String convertDateToFormatString(java.util.Date utilDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return format.format(utilDate);
    }
}
