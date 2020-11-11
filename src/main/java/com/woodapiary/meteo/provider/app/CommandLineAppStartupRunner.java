/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.service.OwDirectoryService;

@Component
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
