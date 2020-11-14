/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.woodapiary.meteo_p")
@EnableJpaRepositories(basePackages = "com.woodapiary.meteo_p")
@EntityScan(basePackages = "com.woodapiary.meteo_p")
public class MeteoProviderApplication {

    public static void main(String[] args) {
        try {
            //TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
            new SpringApplicationBuilder(MeteoProviderApplication.class)
                    .web(WebApplicationType.SERVLET)
                    .run(args);
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
//TODO авторизация на рест и вебсервис
//TODO  com.google.common.collect.Lists;
//TODO List<Singer> findAll() return Lists.newArrayList(singerRepository.findAll());
//TODO аудит
//TODO версионирование
//TODO глобальные транзакции
//TODO тестовые классы мессаджей сделать через бины
