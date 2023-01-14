package edu.school21.cinema.listeners;

import edu.school21.cinema.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class ContextListener
        implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.setAttribute("springContext", new AnnotationConfigApplicationContext(ApplicationConfig.class));
    }
}
