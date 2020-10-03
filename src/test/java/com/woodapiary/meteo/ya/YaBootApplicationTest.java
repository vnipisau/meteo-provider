/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.woodapiary.meteo.ya")
public class YaBootApplicationTest {

    public static void main(final String[] args) {
        SpringApplication.run(YaBootApplicationTest.class, args);
    }

}
