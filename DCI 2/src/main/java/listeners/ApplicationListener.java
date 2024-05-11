package org.jan.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.jan.apiservlet.webapp.headers.models.Carro;

@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener , HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando la aplicación!");;
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje","algún valor global de la app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("se cayo la aplicación!");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        servletContext.log("Inicializando request");
        sre.getServletRequest().setAttribute("mensaje","guardado algún valor para el request");
        sre.getServletRequest().setAttribute("title","Catalogo");
    }


    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo request");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando sesión http");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo sesión http");

    }
}