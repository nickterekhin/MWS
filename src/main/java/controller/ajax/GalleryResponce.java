package controller.ajax;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import controller.Controller;
import db.FlatJDBC;
import domain.Photo;
import helpers.Responder;
import helpers.UploadImageHelper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 25.05.2015.
 */
public class GalleryResponce extends Controller {

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        if(isPermissions(request,"Users")) {
            switch (request.getParameter("a")) {
                case "upload":
                    String res = this.fileUpload(request);
                    Gson jsonObj = new Gson();
                    JsonObject elem = jsonObj.fromJson(res, JsonObject.class);
                    if (!elem.getHtml().isEmpty()) {
                        Photo p = new Photo();
                        if (!db.getFlatJDBC().isPhotoInGalleryByFileName(Long.parseLong(request.getParameter("flatId")), elem.getFileName(), p)) {
                            db.getFlatJDBC().setPhotoInGallery(Long.parseLong(request.getParameter("flatId")), elem.getHtml(), elem.getFileName(), elem.getPos());
                        } else {
                            p.setPhotoName(elem.getHtml());
                            db.getFlatJDBC().updatePhotoInGallery(p);
                        }
                    }
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(res);
                    break;
                case "showDescription":

                    Responder responder = new Responder();
                    Gson gobj = new Gson();
                    Map<String, String> map = new HashMap<>();
                    map.put("description", db.getFlatJDBC().getPhotoDescription(Long.parseLong(request.getParameter("pid")), Long.parseLong(request.getParameter("flatId"))));
                    String description = gobj.toJson(map);
                    String js_respond = "";
                    js_respond += "gl.ShowDescription(" + description + ");";
                    responder.script(js_respond);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(responder.getOutput());
                    break;
                case "AddMessage":
                    db.getFlatJDBC().addDescriptionToPhoto(Long.parseLong(request.getParameter("pos")), Long.parseLong(request.getParameter("flatId")), request.getParameter("dsc"));

                    break;
                case "DelPhoto":
                    Photo p = db.getFlatJDBC().getPhoto(Long.parseLong(request.getParameter("pos")),Long.parseLong(request.getParameter("flatId")));
                    if(p!=null)
                    {
                        String imagePath = request.getServletContext().getRealPath("")+File.separator+"upload"+File.separator+"flats"+File.separator+p.getPhotoName();
                        String imageThumbPath = request.getServletContext().getRealPath("")+File.separator+"upload"+File.separator+"flats"+File.separator+"thumbs"+File.separator+p.getPhotoName();
                        File f = new File(imagePath);
                        File f_thumb = new File(imageThumbPath);
                        if(f.exists() && f_thumb.exists())
                        {
                            f.delete();
                            f_thumb.delete();
                            db.getFlatJDBC().deletePhotoFromGallery(Long.parseLong(request.getParameter("pos")),Long.parseLong(request.getParameter("flatId")));
                        }

                    }
                    break;

            }

        }
        else
        {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("login please");
        }

    }

    private String fileUpload(HttpServletRequest request)
    {

        UploadImageHelper upImage = new UploadImageHelper(request,request.getParameter("updir"),request.getServletContext().getRealPath(""),request.getParameter("divid"),request.getParameter("flatId")+"_"+user.getUid()+"_"+request.getParameter("divid"));
        return  upImage.uploadImage();

    }

    private class JsonObject{
        private String error;
        private String msg;
        private String html;
        private String fileName;
        private int position;

        public JsonObject() {
        }

        public int getPos() {
            return position;
        }

        public void setPos(int pos) {
            this.position = pos;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
