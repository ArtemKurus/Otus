package org.otus.akurus.controller;

import lombok.RequiredArgsConstructor;
import org.otus.akurus.events.EventsPublisher;
import org.otus.akurus.service.TestService;
import org.otus.akurus.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Scanner;

@RequiredArgsConstructor
@ShellComponent
public class ShellController {

    private final EventsPublisher eventsPublisher;
    private final TestService testService;
    private final UserService userService;
    private final MessageSource messageSource;

    @ShellMethod(value = "Run Test", key = {"run","r"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void runTest() {
        testService.run(new Scanner(System.in));
    }

    @ShellMethod(value = "Run test use event", key = {"erun","er"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void eventRunTest() {
        eventsPublisher.publish();
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "Artem") String userName, @ShellOption(defaultValue = "Kurus") String userSurname) {
        userService.createUser(userName, userSurname);
        return String.format(messageSource.getMessage("test.welcome", null, LocaleContextHolder.getLocale()) + ": %s", userName);
    }

    private Availability isPublishEventCommandAvailable() {
        return userService.isUser();
    }
}
