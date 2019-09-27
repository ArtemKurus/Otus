package org.otus.akurus;

import org.otus.akurus.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		TestService test = context.getBean(TestService.class);
		test.run();
	}

}
