/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.woodapiary.meteo.provider")
public class MeteoProviderApplicationTest {

    public static void main(final String[] args) {
        SpringApplication.run(MeteoProviderApplicationTest.class, args);
    }

}
