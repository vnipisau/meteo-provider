/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "meteo-provider.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Profile("!dev")
public class SchedulingConfiguration {
}
