package controller.account;

import controller.Controller;
import domain.Contact;
import domain.ContactType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Nick on 28.06.2015.
 */
public class AccountController extends Controller {
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {
        if(isPermissions(request,"Users")) {
            WebContext ctxin = new WebContext(request,response,ctx,request.getLocale());
            String action = request.getParameter("action");
            if(action!=null)
            {
                switch(action)
                {
                    case "openclosed":
                        db.getUsers().OpenClosedContact(Long.parseLong(request.getParameter("cid")),request.getParameter("a"));
                        List<Contact> c = db.getUsers().getContactsAccount(user.getUid());
                        ctxin.setVariable("cont", c);
                        tmpl.process("account/account", ctxin, response.getWriter());
                        break;
                    case "editcontact":
                        Contact contact = db.getUsers().getContactById(Long.parseLong(request.getParameter("cid")));
                        List<ContactType> ct1 = db.getUsers().getContactTypes();
                        ctxin.setVariable("contact",contact);
                        ctxin.setVariable("conTypes", ct1);
                        tmpl.process("account/addaccount", ctxin, response.getWriter());
                        break;
                    case "addcontact":
                        if(request.getMethod().equals("POST") && Integer.valueOf(request.getParameter("send"))==1){
                        Contact contact1 = new Contact(user.getUid(),Long.parseLong(request.getParameter("contactType")),request.getParameter("contactValue"),Integer.parseInt(request.getParameter("contactPos")),true);
                            db.getUsers().setContact(contact1);
                            response.getWriter().write("<script>window.opener.location.reload(); window.close();</script>;");
                        }
                        else {
                            List<ContactType> ct = db.getUsers().getContactTypes();
                            ctxin.setVariable("conTypes", ct);
                            tmpl.process("account/addaccount", ctxin, response.getWriter());
                        }
                        break;
                    case "delcontact":
                            db.getUsers().deleteContact(Long.parseLong(request.getParameter("cid")));
                        List<Contact> c2 = db.getUsers().getContactsAccount(user.getUid());
                        ctxin.setVariable("cont", c2);
                        tmpl.process("account/account", ctxin, response.getWriter());
                        break;
                }
            }else {
                List<Contact> c = db.getUsers().getContactsAccount(user.getUid());
                ctxin.setVariable("cont", c);
                tmpl.process("account/account", ctxin, response.getWriter());
            }
        }
        else
        {

        }
    }
}
