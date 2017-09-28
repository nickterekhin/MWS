package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.FlatJDBC;
import domain.Flat;
import domain.Photo;
import domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 21.06.2015.
 */
public class UploadImageHelper {
    private String UPLOAD_DIRECTORY = "/upload/";

    // upload settings
    private final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    private  HttpServletRequest req;
    private String servletPath;
    private String divid;
    private String customFileName = String.valueOf(DateTime.now().getMillis()/1000);
    private int resize = 300;

    public UploadImageHelper(HttpServletRequest req, String servletPath, String divid, String customFileName) {
        this.req = req;
        this.servletPath = servletPath;
        this.divid=divid;
        this.customFileName = customFileName;
    }

    public UploadImageHelper(HttpServletRequest req, String servletPath, String divid) {
        this.req = req;
        this.servletPath = servletPath;
        this.divid=divid;
    }

    public UploadImageHelper(HttpServletRequest req, String UPLOAD_DIRECTORY,String servletPath, String divid, String customFileName) {
        this.req = req;
        this.UPLOAD_DIRECTORY = UPLOAD_DIRECTORY;
        this.servletPath = servletPath;
        this.divid=divid;
        this.customFileName = customFileName;
    }

    public String uploadImage()
    {
        if(ServletFileUpload.isMultipartContent(req)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);

            upload.setSizeMax(MAX_REQUEST_SIZE);

            String uploadPath = servletPath + File.separator + UPLOAD_DIRECTORY;

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean mkdir = uploadDir.mkdir();
            }

            try {

                List<FileItem> formItems = upload.parseRequest(req);

                if (formItems != null && formItems.size() > 0) {

                    for (FileItem item : formItems) {

                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            String ext = FilenameUtils.getExtension(fileName);
                            long timeStamp = DateTime.now().getMillis()/1000;
                            String newFile = this.customFileName+"."+ext;
                            String filePath = uploadPath + File.separator + newFile;
                            File storeFile = new File(filePath);
                            isPhoto(uploadPath, this.customFileName);
                            item.write(storeFile);
                            String imageToCrop = ImageFunctions.resize(filePath, uploadPath, newFile, this.resize);
                            ImageFunctions.createThumbnail(imageToCrop,uploadPath+File.separator+"thumbs",newFile,100,100);
                            Gson gobj = new Gson();
                            Map<String,String> map = new HashMap<String,String>();
                            map.put("error","");
                            map.put("msg","<img src='"+UPLOAD_DIRECTORY+"thumbs/" + newFile+  "?"+timeStamp+"' id='imgall_"+this.divid+"' />");
                            map.put("html",newFile);
                            map.put("fileName",this.customFileName);
                            map.put("position",divid);


                            return gobj.toJson(map);

                        }
                    }
                }
            } catch (Exception ex) {
                Gson gobj = new Gson();
                Map<String,String> map = new HashMap<String,String>();
                map.put("error","There was an error: " + ex.getMessage());
                return gobj.toJson(map);

            }

        }
        else {
            Gson gobj = new Gson();
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", "No file ro upload");
            return gobj.toJson(map);
        }
        return null;
    }
    private boolean isPhoto(String path,String fileNameWithoutExt)
    {
        isThumbPhoto(path,fileNameWithoutExt);
        File dir = new File(path);
        FileFilter filter = new WildcardFileFilter(fileNameWithoutExt+".*");
        File[] files =  dir.listFiles(filter);
        if(files.length>0)
            return files[0].delete();

        return false;
    }
    private boolean isThumbPhoto(String path,String fileNameWithoutExt)
    {
        File dir = new File(path+File.separator+"thumbs");
        FileFilter filter = new WildcardFileFilter(fileNameWithoutExt+".*");
        File[] files =  dir.listFiles(filter);
        if(files!=null && files.length>0)
            return files[0].delete();

        return false;
    }

    public int getResize() {
        return resize;
    }

    public void setResize(int resize) {
        this.resize = resize;
    }
}
