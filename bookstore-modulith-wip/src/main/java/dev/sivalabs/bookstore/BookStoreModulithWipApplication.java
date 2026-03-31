package dev.sivalabs.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
@EnableCaching
public class BookStoreModulithWipApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreModulithWipApplication.class, args);
    }

}
