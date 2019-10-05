package org.otus.akurus.Controller;

import org.otus.akurus.service.TestService;
import org.otus.akurus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineController implements CommandLineRunner {

    private final TestService testService;
    private final UserService userService;

    @Autowired
    public CommandLineController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser();
        testService.run();
    }
}
