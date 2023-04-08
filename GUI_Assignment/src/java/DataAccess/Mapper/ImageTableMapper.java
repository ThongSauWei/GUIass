package DataAccess.Mapper;

/**
 * @author LOH XIN JIE<br/>
 * in image table have 4 col<br/>
 * in image table trans_id is long type of date
 */
import Model.ImageTable;
import java.sql.*;

public class ImageTableMapper extends RowMapper<ImageTable> {

    public ImageTableMapper() {
        super("trans_id");
    }

    @Override
    public ImageTable mapRow(ResultSet result) throws SQLException {
        //get output in byte[]
        return new ImageTable(result.getString(TRANS_ID),
                result.getString(IMAGE_NAME),
                result.getString(IMAGE_CONTENT),
                result.getBytes(IMAGE));
    }

    @Override
    public PreparedStatement prepareAdd(Connection conn, ImageTable imageTable) throws SQLException {
        String sqlQuery = "INSERT INTO " + imageTable.TABLENAME
                + " (" + TRANS_ID + ", "
                + IMAGE_NAME + ", "
                + IMAGE_CONTENT + ", "
                + IMAGE + ") "
                + "VALUES(?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, imageTable.getTransId());
        stmt.setString(2, imageTable.getImageName());
        stmt.setString(3, imageTable.getImageContentType());
        stmt.setBlob(4, imageTable.getInputImage());
        return stmt;
    }

    @Override
    public PreparedStatement prepareUpdate(Connection conn, ImageTable imageTable) throws SQLException {
        String sqlQuery = "Update " + imageTable.TABLENAME + " SET "
                + IMAGE_NAME + " = ?, "
                + IMAGE_CONTENT + " = ?, "
                + IMAGE + " = ? WHERE " + TRANS_ID + " = ?";

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(4, imageTable.getTransId());
        stmt.setString(1, imageTable.getImageName());
        stmt.setString(2, imageTable.getImageContentType());
        stmt.setBlob(3, imageTable.getInputImage());
        return stmt;
    }

    @Override
    public PreparedStatement prepareDelete(Connection conn, ImageTable imageTable) throws SQLException {
        String sqlQuery = "DELETE FROM " + imageTable.TABLENAME + " WHERE " + TRANS_ID + " = ?";

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.setString(1, imageTable.getTransId());

        return stmt;
    }
}
