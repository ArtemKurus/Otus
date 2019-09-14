package org.otus.akurus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    static final Logger rootLogger = LogManager.getRootLogger();

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Test test = (Test) applicationContext.getBean("test");
        test.run();
    }
}
