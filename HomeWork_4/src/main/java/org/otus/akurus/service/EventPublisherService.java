package org.otus.akurus.service;

import lombok.RequiredArgsConstructor;
import org.otus.akurus.events.EventsPublisher;
import org.otus.akurus.events.StartTestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisherService implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new StartTestEvent(this));
    }
}
