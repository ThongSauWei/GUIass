package Controller;

/**
 * @author LOH XIN JIE
 */
import DataAccess.*;
import DataAccess.Mapper.ImageTableMapper;
import Model.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "C:/tmp"
)//max file size upload up to 10MB
public class RetrieveImageServlet extends HttpServlet {

    private DBTable db;

    /**
     * for GET and POST method use
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse respone) throws ServletException, IOException {
        String imageID = request.getParameter("imageID");

        try {
            if (imageID != null) {
                //use trans id to get image
                int id = Integer.parseInt(imageID);
                ImageTable itable = db.ImageTable.getData(new ImageTableMapper(), id).get(0);

                //set return info
                respone.setContentType(itable.getImageContentType());
                OutputStream os = respone.getOutputStream();
                os.write(itable.getOutputImage());
                os.flush();
                os.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respone.getOutputStream().flush();
            respone.getOutputStream().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        db = new DBTable();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        //processRequest(request, response);
        try {
            ImageTableController iControl = new ImageTableController();
            iControl.UpdateImage(request);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
