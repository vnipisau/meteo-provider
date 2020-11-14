package com.woodapiary.meteo_p.config;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.woodapiary.meteo_p.app.config.ModelMapperConfig;

public class ModelMapperConfigTest {

    @Test
    public void test01() {
        try (GenericApplicationContext ctx = new AnnotationConfigApplicationContext(
                ModelMapperConfig.class)) {
            final Map<String, ModelMapper> beans = ctx.getBeansOfType(ModelMapper.class);
            assertTrue(beans.entrySet().size() > 0);
            /*
             * beans.entrySet().stream().forEach(b -> System.out.println("id: " + b.getKey() + "\n aliases: " +
             * Arrays.toString(ctx.getAliases(b.getKey())) + "\n"));
             */
            ctx.close();
        }

    }

}
