package org.otus.akurus.events;

import lombok.AllArgsConstructor;
import org.otus.akurus.service.TestService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@AllArgsConstructor
@Component
public class StartTestEventListner implements ApplicationListener<StartTestEvent> {

    private final TestService testService;

    @Override
    public void onApplicationEvent(StartTestEvent startTestEvent) {
        testService.run(new Scanner(System.in));
    }
}
