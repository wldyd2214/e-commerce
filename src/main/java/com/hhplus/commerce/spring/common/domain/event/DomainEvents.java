package com.hhplus.commerce.spring.common.domain.event;

import org.springframework.context.ApplicationEventPublisher;

public class DomainEvents {

    private static ApplicationEventPublisher publisher;

    public static void setPublisher(ApplicationEventPublisher publisher) {
        DomainEvents.publisher = publisher;
    }

    public static void raise(Object event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }
}
