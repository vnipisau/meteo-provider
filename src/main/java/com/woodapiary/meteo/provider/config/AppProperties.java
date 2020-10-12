/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:meteo-provider.properties")
public class AppProperties {

}
