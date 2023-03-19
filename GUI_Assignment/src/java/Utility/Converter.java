package Utility;

/**
 * @author LOH XIN JIE
 */
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
}
