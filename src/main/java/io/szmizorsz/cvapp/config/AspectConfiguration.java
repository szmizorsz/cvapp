package io.szmizorsz.cvapp.config;

import io.szmizorsz.cvapp.aop.logging.LoggingAspect;
import io.szmizorsz.cvapp.aop.logging.NotificationAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Bean
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public NotificationAspect notificationAspect() {
        return new NotificationAspect();
    }
}
