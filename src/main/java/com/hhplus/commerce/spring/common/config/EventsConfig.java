package com.hhplus.commerce.spring.common.config;

import com.hhplus.commerce.spring.common.domain.event.DomainEvents;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventsConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventsInitializer() {
        return () -> DomainEvents.setPublisher(applicationContext);
    }
}
