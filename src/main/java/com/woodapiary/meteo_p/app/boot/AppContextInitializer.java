package com.woodapiary.meteo_p.app.boot;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppContextInitializer
        implements ApplicationContextInitializer {

    //FIXME not work
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ConfigurableApplicationContext.id - " + applicationContext.getId());
    }
}
