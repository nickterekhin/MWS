package controller.ajax;

import com.google.gson.Gson;
import controller.Controller;
import helpers.UploadImageHelper;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Nick on 05.07.2015.
 */
public class AvatarResponce extends Controller {
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        if(isPermissions(request,"Users")) {
            switch (request.getParameter("a")) {
                case "upload":
                    String res = this.fileUpload(request);
                    Gson jsonObj = new Gson();
                    JsonObject elem = jsonObj.fromJson(res,JsonObject.class);
                    if(!elem.getHtml().isEmpty())
                    {
                        db.getUsers().setAvatar(user.getUid(),elem.getHtml());
                    }
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(res);
                    break;
                case "DelPhoto":
                    String imagePath = request.getServletContext().getRealPath("")+ File.separator+"upload"+File.separator+"users"+File.separator+user.getAvatar();
                    String imageThumbPath = request.getServletContext().getRealPath("")+ File.separator+"upload"+File.separator+"users"+File.separator+"thumbs"+File.separator+user.getAvatar();

                    File f = new File(imagePath);
                    File f_thumb = new File(imageThumbPath);
                    if(f.exists() && f_thumb.exists())
                    {
                        f.delete();
                        f_thumb.delete();
                        db.getUsers().removeAvatar(user.getUid());
                    }
                    break;
            }
        }

    }

    private String fileUpload(HttpServletRequest request)
    {
        UploadImageHelper upImage = new UploadImageHelper(request,request.getParameter("updir"),request.getServletContext().getRealPath(""),request.getParameter("divid"),user.getUid().toString());
        upImage.setResize(100);
        return upImage.uploadImage();
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
