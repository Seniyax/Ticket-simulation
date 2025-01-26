package org.w2052932.backend;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext apc =  SpringApplication.run(BackendApplication.class, args);
        for (String s : apc.getBeanDefinitionNames()){
            System.out.println(s);

        }
    }

}
