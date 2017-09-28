package controller;

import domain.User;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Nick on 15.03.2015.
 */
public class LogoutController extends Controller {
    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception {

        Cookie userCookie =null;
        Cookie passwdCookie =null;
        Cookie[] cookies = request.getCookies();
        HttpSession session  = request.getSession(false);

        if(session!=null && session.getAttribute("user")!=null && (Boolean)session.getAttribute("isLogged")) {
            session.invalidate();
        }
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("mws3627738_uid"))
                    userCookie = cookie;
                if(cookie.getName().equals("mws3627738_pwd"))
                    passwdCookie = cookie;

            }
            if (userCookie!=null && passwdCookie!=null) {

                userCookie.setMaxAge(0);
                passwdCookie.setMaxAge(0);
                response.addCookie(userCookie);
                response.addCookie(passwdCookie);

            }
        }
        response.sendRedirect("home");
    }
}
