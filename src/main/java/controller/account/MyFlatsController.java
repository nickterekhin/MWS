package controller.account;

import controller.Controller;
import domain.Flat;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Nick on 02.04.2015.
 */
public class MyFlatsController extends Controller {
    private List<String> errors;
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        if(isPermissions(request,"Users")) {
            WebContext ctxin = new WebContext(request,response,ctx,request.getLocale());

            if(request.getMethod().equals("GET") && request.getParameter("action")!=null && !request.getParameter("action").isEmpty()){
                db.getFlatJDBC().flatAction(request.getParameter("action"),Long.parseLong(request.getParameter("flatId")));
            }

            List<Flat> flats = db.getFlatJDBC().getAllFlatsByUserId(user.getUid());
            ctxin.setVariable("flats",flats);
            tmpl.process("account/myflats", ctxin, response.getWriter());
        }
        else
        {

        }
    }


}
