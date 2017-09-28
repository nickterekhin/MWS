package controller;

import domain.User;
import org.joda.time.DateTime;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Nick on 06.03.2015.
 */
public class LoginControl extends Controller  {

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {


        if (request.getParameter("send") != null && !request.getParameter("send").isEmpty() && Integer.valueOf(request.getParameter("send")) == 1)
        {
            if(request.getParameter("user_name")!=null && !request.getParameter("user_name").isEmpty() &&request.getParameter("user_passwd")!=null && !request.getParameter("user_passwd").isEmpty())
            {
                User u = db.getUsers().getByPassAndUserName(md5(md5(request.getParameter("user_passwd"))),request.getParameter("user_name"));
                if(u!=null && u.getUgroup()!=2)
                {
                    Cookie uid = new Cookie("mws3627738_uid",u.getUserName());
                    Cookie pass = new Cookie("mws3627738_pwd",u.getPassword());
                    uid.setMaxAge((int)(DateTime.now().getMillis()/1000) + (365 * 24 * 3600));
                    pass.setMaxAge((int)(DateTime.now().getMillis()/1000) + (365 * 24 * 3600));
                    response.addCookie(uid);
                    response.addCookie(pass);
                }
            }
        }

        response.sendRedirect("home");
    }


}
