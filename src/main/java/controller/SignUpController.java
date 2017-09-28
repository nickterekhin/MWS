package controller;

import domain.User;
import org.joda.time.DateTime;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 15.03.2015.
 */
public class SignUpController extends Controller {
    private List<String> errors;

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {
        if(!user.getIsLoged())
        {
            WebContext ctxin = new WebContext(request,response,ctx,request.getLocale());
            if(request.getMethod().equals("POST") && Integer.valueOf(request.getParameter("send"))==1) {
                if (validation(request)) {
                    User u =new User(3L,request.getParameter("userName"),md5(md5(request.getParameter("passwd"))),request.getParameter("firstName"),request.getParameter("lastName"), DateTime.now(),false);
                    db.getUsers().createUser(u);
                    ctxin.setVariable("done", 1);
                    ctxin.setVariable("vmMessage","Thank You for registration in our system! Please use your UserName and password to login");
                } else {
                    ctxin.setVariable("warn", true);
                    ctxin.setVariable("errors", errors);
                }
            }
            tmpl.process("pages/signup",ctxin,response.getWriter());
        }
        else
        {
            response.sendRedirect("/");
        }
    }

    private boolean validation(HttpServletRequest req)
    {
        errors = new ArrayList<>();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String passwd = req.getParameter("passwd");
        if(firstName.isEmpty())
            errors.add("Field: FirstName is required!!!");
        if(lastName.isEmpty())
            errors.add("Field: LastName is required!!!");
        if(userName.isEmpty()) {
            errors.add("Field: UserName is required!!!");
        }
        else if(db.getUsers().getByUserName(userName)!=null){
            errors.add("UserName already exist!!1 Please choose another");
        }
        if(passwd.isEmpty())
            errors.add("Field: Password is required!!!");

        if(errors.size()==0)
            return true;
        return false;
    }
}
