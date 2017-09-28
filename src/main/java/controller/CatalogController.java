package controller;

import domain.Contact;
import domain.Flat;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Nick on 21.03.2015.
 */
public class CatalogController extends Controller {
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        WebContext ctxin = new WebContext(request, response, ctx, request.getLocale());
        String action = request.getParameter("action");
        if(action != null) {
            switch (action) {
                case "showflat":
                    Flat f = db.getFlatJDBC().getFlatById(Long.parseLong(request.getParameter("flatId")));
                    List<Contact> contacts = db.getUsers().getContacts(f.getUid());
                    ctxin.setVariable("flat", f);
                    ctxin.setVariable("us",db.getUsers().getById(f.getUid()));
                    ctxin.setVariable("contacts",contacts);
                    tmpl.process("catalog/showflat", ctxin, response.getWriter());
                    break;
            }
        }
        else
        {
            List<Flat> flats = db.getFlatJDBC().getAllFlats();
            ctxin.setVariable("flats",flats);
            tmpl.process("catalog/allflats", ctxin, response.getWriter());
        }

    }
}
