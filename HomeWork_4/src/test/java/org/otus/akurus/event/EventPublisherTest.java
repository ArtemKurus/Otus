package org.otus.akurus.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.otus.akurus.events.StartTestEvent;
import org.otus.akurus.service.EventPublisherService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventPublisherTest {

    private EventPublisherService eventPublisherService;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    protected ArgumentCaptor<StartTestEvent> publishEventCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventPublisherService = new EventPublisherService(applicationEventPublisher);
    }

    @Test
    public void testEvent() {
        eventPublisherService.publish();
        Mockito.verify(applicationEventPublisher).publishEvent(publishEventCaptor.capture());
        StartTestEvent capturedEvent = publishEventCaptor.getValue();
        assertEquals(StartTestEvent.class.getName(), capturedEvent.getClass().getName());
    }


}
