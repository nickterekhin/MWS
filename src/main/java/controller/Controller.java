package controller;

import db.jdbcadapter.DbEntities;
import domain.Group;
import domain.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;


/**
 * Created by Nick on 04.03.2015.
 */
public abstract class Controller  {
    protected DbEntities db = new DbEntities();
    protected User user;
    protected Group group;
    protected List<String> errors;
    public void userInit(HttpServletRequest request, HttpServletResponse response) {

        User usr = (User) request.getSession(false).getAttribute("user");
        if((usr == null) || (usr.getUgroup() == 2))
        {
            String userName = null;
            String passwd=null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("mws3627738_uid"))
                        userName = cookie.getValue();
                    if(cookie.getName().equals("mws3627738_pwd"))
                        passwd = cookie.getValue();

                }
                if (userName!=null && passwd!=null) {

                    request.getSession(false).setAttribute("user", db.getUsers().getByPassAndUserName(passwd, userName));

                }
                else
                {
                    request.getSession(false).setAttribute("user",new User());
                }
            }
            else
            {
                request.getSession(false).setAttribute("user",new User());
            }


        }


        user = (User) request.getSession(false).getAttribute("user");
        request.getSession(false).setAttribute("isLogged", user.getIsLoged());
        group = db.getGroups().getByGroupId(user.getUgroup());
        request.getSession(false).setAttribute("UserGroup", group );


    }

    public abstract void getModel(HttpServletRequest request, HttpServletResponse response, ServletContext ctx, TemplateEngine tmpl) throws Exception ;

    protected String md5(String str)
    {
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] data = str.getBytes();
            m.update(data,0,data.length);
            BigInteger i = new BigInteger(1,m.digest());
            return i.toString(16);

        }catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean isPermissions(HttpServletRequest request, String perm)
    {
        group  = (Group)request.getSession(false).getAttribute("UserGroup");
        if(group !=null)
        {
            Set<String> permission = group.getPermission();
            if( permission !=null)
            {
                return permission.contains(perm);
            }
        }

        return false;
    }

    protected void TemplateProcess(TemplateEngine tmp,WebContext ctx, HttpServletResponse response) throws IOException {
        tmp.process("account/account", ctx, response.getWriter());
    }
}
