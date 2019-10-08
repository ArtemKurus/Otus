package org.otus.akurus.events;

import org.springframework.context.ApplicationEvent;

public class StartTestEvent extends ApplicationEvent {
    public StartTestEvent(Object source) {
        super(source);
    }
}
