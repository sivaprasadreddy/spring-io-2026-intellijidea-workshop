package dev.sivalabs.bookstore.config;

import dev.sivalabs.bookstore.ApplicationProperties;
import dev.sivalabs.bookstore.domain.service.ConsoleLoggingEmailService;
import dev.sivalabs.bookstore.domain.service.EmailService;
import dev.sivalabs.bookstore.domain.service.JavaMailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

//@Configuration
class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email-service-type", havingValue = "console")
    EmailService consoleLoggingEmailService(ApplicationProperties props) {
        return new ConsoleLoggingEmailService(props);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email-service-type", havingValue = "javamail", matchIfMissing = true)
    EmailService javaMailService(JavaMailSender javaMailSender, ApplicationProperties props) {
        return new JavaMailService(javaMailSender, props);
    }
}
