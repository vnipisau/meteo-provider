/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.woodapiary.meteo.provider")
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.woodapiary.meteo.provider")
@EntityScan(basePackages = "com.woodapiary.meteo.provider.entity")
//@EnableScheduling
public class MeteoProviderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            //TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
            SpringApplication.run(MeteoProviderApplication.class, args);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}

//TODO Аспект для логирования
//TODO Пул потоков для асинхронных задач
//TODO Хуки при старте и останове сервера, при запросах
//TODO дао для сорца, через jdbc template, get by provider and place.
//TODO gsmeteo
//TODO metar
//TODO created and modified
//TODO yandex version 2 
//TODO UUID
//TODO ELK
//TODO batch save
//TODO авторизация на рест
