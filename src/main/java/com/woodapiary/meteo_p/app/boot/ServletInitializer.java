package com.woodapiary.meteo_p.app.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.woodapiary.meteo_p.MeteoProviderApplication;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MeteoProviderApplication.class);
    }

    //FIXME not work
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("========" + servletContext.getContextPath() + "========");
    }

}
