package com.woodapiary.meteo.provider.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class WebAppInitializer
        implements WebApplicationInitializer {

    //TODO не срабатывает
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("========" + servletContext.getContextPath() + "========");
    }
}
