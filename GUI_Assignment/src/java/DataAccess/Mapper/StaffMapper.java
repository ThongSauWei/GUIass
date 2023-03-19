package DataAccess.Mapper;

/**
 * @author LOH XIN JIE
 */
import Model.Staff;
import java.sql.*;

public class StaffMapper extends RowMapper<Staff> {

    public StaffMapper() {
        super("staff_id");
    }

    @Override
    public Staff mapRow(ResultSet result) throws SQLException {
        return new Staff(result.getInt(STAFF_ID), result.getString(STAFF_NAME), result.getString(STAFF_PASS), result.getString(STAFF_IC), result.getString(STAFF_PHNO), result.getString(STAFF_EMAIL), result.getDate(STAFF_BIRTHDATE));
    }
}
