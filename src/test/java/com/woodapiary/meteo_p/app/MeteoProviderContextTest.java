package com.woodapiary.meteo_p.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeteoProviderContextTest {

    @Autowired
    private ApplicationContext appContext;

    @Test
    public void test02() {
        System.out.println("app name: " + appContext.getApplicationName());
        System.out.println("id: " + appContext.getId());
        System.out.println("env: " + appContext.getEnvironment().toString());
    }

}
