/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "com.woodapiary.meteo.ya")
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.woodapiary.meteo.ya")
@EntityScan(basePackages = "com.woodapiary.meteo.ya.entity")
@EnableScheduling
public class YaBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YaBootApplication.class, args);
    }

}
