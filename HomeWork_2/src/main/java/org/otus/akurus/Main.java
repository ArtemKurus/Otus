package org.otus.akurus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.otus.akurus.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "org.otus")
@Configuration
public class Main {
    static final Logger rootLogger = LogManager.getRootLogger();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestService test = context.getBean(TestService.class);
        test.run();
    }
}
