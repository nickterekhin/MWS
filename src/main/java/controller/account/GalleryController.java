package controller.account;

import controller.Controller;
import domain.Flat;
import domain.Photo;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 09.05.2015.
 */
public class GalleryController extends Controller{
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        if(isPermissions(request,"Users")) {
            WebContext ctxin = new WebContext(request, response, ctx, request.getLocale());


            Long flatId = Long.parseLong(request.getParameter("flatId"));
            List<Photo> photo = db.getFlatJDBC().getPhotosFromGallery(flatId);
            Photo[] p = new Photo[14];
            for (Photo item : photo) {
                p[item.getPosition() - 1] = item;
            }

            ctxin.setVariable("num_gall", p.length - 1);

            ctxin.setVariable("photo", p);

            tmpl.process("account/gallery", ctxin, response.getWriter());
        }
        else
        {

        }
    }

    private class TestVars{
        public String myName = "Nick";
        public String mySurName = "Terekhin";
        public String image= "2_1.jpg";
    }
}
