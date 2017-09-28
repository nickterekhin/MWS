package controller;

import controller.account.AccountController;
import controller.account.AddFlatController;
import controller.account.GalleryController;
import controller.account.MyFlatsController;
import controller.ajax.AvatarResponce;
import controller.ajax.GalleryResponce;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import uk.co.gcwilliams.jodatime.thymeleaf.JodaTimeDialect;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 13.03.2015.
 */
public class ControllerFactory {

    private static Map<String,Controller> actions;
    private static TemplateEngine templateEngine;
    static{
        initializerControllersByUrl();
        initializerTemplateEngine();
    }

    private static void initializerTemplateEngine(){

        ServletContextTemplateResolver templateresolver = new ServletContextTemplateResolver();

        templateresolver.setTemplateMode("HTML5");
        templateresolver.setPrefix("/templates/");
        templateresolver.setSuffix(".jsp");
        templateresolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateresolver.setCacheable(false);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateresolver);
        templateEngine.addDialect(new JodaTimeDialect());
        templateEngine.addDialect(new LayoutDialect());


    }

    private static Map<String,Controller> initializerControllersByUrl(){
        actions = new HashMap<String,Controller>();
        actions.put("/", new IndexController());
        actions.put("/home", new IndexController());
        actions.put("/login", new LoginControl());
        actions.put("/logout",new LogoutController());
        actions.put("/pages/description",new DescriptionController());
        actions.put("/pages/signup",new SignUpController());
        actions.put("/catalog/allflats",new CatalogController());
        //actions.put("/catalog/showflat",new CatalogController());
        actions.put("/account/myflats",new MyFlatsController());
        actions.put("/account/addflat", new AddFlatController(false));
        actions.put("/account/editflat", new AddFlatController(true));
        actions.put("/account/gallery",new GalleryController());
        actions.put("/account/account",new AccountController());
        actions.put("/ajax/gallery",new GalleryResponce());
        actions.put("/ajax/avatar",new AvatarResponce());
        return actions;
    }

    public static Controller getAction(HttpServletRequest request) {

        String path= getRequestPath(request);
        return actions.get(path);
    }

    public static TemplateEngine getTemplateEngine()
    {
        return templateEngine;
    }

    private static String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();
        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }
        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }

    private ControllerFactory(){
        super();
    }
}
