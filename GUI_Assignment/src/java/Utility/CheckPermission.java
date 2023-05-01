package Utility;

/**
 * @author LOH XIN JIE
 */
import javax.servlet.http.*;

public class CheckPermission {

    public static boolean permissionUser(HttpServletRequest request) {
        return request.getSession().getAttribute("member") != null;
    }

    public static boolean permissionStaff(HttpServletRequest request) {
        return request.getSession().getAttribute("staffLogin") != null;
    }

    public static boolean permissionAdmin(HttpServletRequest request) {
        return request.getSession().getAttribute("staffLogin") != null && ((String) request.getSession().getAttribute("staffLogin")).endsWith("admin");
    }

}
