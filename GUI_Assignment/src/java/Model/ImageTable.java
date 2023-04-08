package Model;

/**
 * @author LOH XIN JIE
 */
import DataAccess.DBModel;
import java.io.InputStream;

public class ImageTable extends DBModel {

    private String transId;
    private String imageName;
    private String imageContentType;
    private InputStream inputImage;
    private byte[] outputImage;

    public ImageTable() {
        super("imagetable");
    }

    public ImageTable(String transId) {
        super("imagetable");
        this.transId = transId;
    }

    //no image
    public ImageTable(String transId, String imageName, String imageContentType) {
        super("imagetable");
        this.transId = transId;
        this.imageName = imageName;
        this.imageContentType = imageContentType;
    }

    //output image  byte[] -> outputStream
    public ImageTable(String transId, String imageName, String imageContentType, byte[] outputImage) {
        super("imagetable");
        this.transId = transId;
        this.imageName = imageName;
        this.imageContentType = imageContentType;
        this.outputImage = outputImage;
    }

    //input image
    public ImageTable(String transId, String imageName, String imageContentType, InputStream inputImage, String tableName) {
        super(tableName);
        this.transId = transId;
        this.imageName = imageName;
        this.imageContentType = imageContentType;
        this.inputImage = inputImage;
    }

    public String getTransId() {
        return transId;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public byte[] getOutputImage() {
        return outputImage;
    }

    public InputStream getInputImage() {
        return inputImage;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public void setOutputImage(byte[] outputImage) {
        this.outputImage = outputImage;
    }

    public void setInputImage(InputStream inputImage) {
        this.inputImage = inputImage;
    }
}
