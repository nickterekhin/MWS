package filters;

import controller.*;
import org.thymeleaf.TemplateEngine;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 06.03.2015.
 */
public class MainEngineFilter implements Filter {

    private ServletContext servletContext;

    public MainEngineFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        //RequestDispatcher requestDispatcher;
        if(!execute(req,resp))
        {
           // requestDispatcher = servletContext.getRequestDispatcher("/templates/other/pageNotFound.jsp");
            //requestDispatcher.forward(req,resp);
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    @Override
    public void destroy() {

    }

    private boolean execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException
    {
        try {
            Controller controller = ControllerFactory.getAction(req);
            if (controller == null)
                return false;

            controller.userInit(req,resp);


            TemplateEngine templateEngine = ControllerFactory.getTemplateEngine();

            resp.setContentType("text/html;charset=utf-8");
            resp.setHeader("Param", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);

            controller.getModel(req, resp, this.servletContext, templateEngine);

            return true;
        }
        catch(Exception e)
        {
            throw new ServletException();
        }
    }
}
