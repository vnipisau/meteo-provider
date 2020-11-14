/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo_p.ow.service.OwDirectoryService;

@Component
@ConditionalOnProperty(name = "meteo-provider.scheduling.enabled", havingValue = "true", matchIfMissing = false)
@Profile("!dev")
public class CommandLineAppStartupRunner implements CommandLineRunner {
    static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    public OwDirectoryService dir;
    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========" + appName + " app is started" + "========");
        dir.startup();
    }
}
