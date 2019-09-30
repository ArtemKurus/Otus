package org.otus.akurus;

import org.otus.akurus.service.TestService;
import org.otus.akurus.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
	    SpringApplication.run(Main.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ctx.getBean(UserServiceImpl.class).createUser();
            ctx.getBean(TestService.class).run();
        };
    }
}
