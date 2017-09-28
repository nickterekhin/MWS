package controller;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by Nick on 06.03.2015.
 */
public class IndexController extends Controller {
    private static int count = 0;

    public IndexController() {
        super();
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        WebContext ctxin = new WebContext(request, response, ctx, request.getLocale());

            ctxin.setVariable("today", Calendar.getInstance());

            tmpl.process("/pages/home", ctxin, response.getWriter());

    }

}
