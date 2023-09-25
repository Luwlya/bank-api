package com.luwlya.bankapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class TimeConfig {
    /**
     * Encapsulates current time to make code testable
     *
     * @return configured instance of clock
     */
    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }
}
