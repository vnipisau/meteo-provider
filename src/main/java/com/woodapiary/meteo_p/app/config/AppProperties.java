/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Configuration
@PropertySource("classpath:meteo-provider.properties")
@Validated
public class AppProperties {

    @Min(value = 5, message = "must be between 5 and 25")
    @Max(value = 25, message = "must be between 5 and 25")
    @Value("${meteo-provider.magic}")
    int magic;

}
