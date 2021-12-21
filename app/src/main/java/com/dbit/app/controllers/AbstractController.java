package com.dbit.app.controllers;

import com.dbit.app.repositories.RepositoryFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AbstractController extends HttpServlet {
    public static final String BASE_PACKAGE = "com.dbit.app";
    public static final String CTX_NAME = "ctx";
    private boolean isFirst = true;
    protected ConfigurableApplicationContext ctx;
    protected String type;


    @Override
    public void init(ServletConfig config) throws ServletException {
        if (isFirst) {
            ctx = new AnnotationConfigApplicationContext(BASE_PACKAGE);
            config.getServletContext().setAttribute(CTX_NAME, ctx);
            isFirst = false;
            type = RepositoryFactory.getType();
        } else {
            ctx = (AnnotationConfigApplicationContext) config.getServletContext().getAttribute(CTX_NAME);
        }
    }
}
