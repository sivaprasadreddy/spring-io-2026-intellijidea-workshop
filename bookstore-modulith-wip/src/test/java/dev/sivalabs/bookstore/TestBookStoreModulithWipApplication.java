package dev.sivalabs.bookstore;

import org.springframework.boot.SpringApplication;

public class TestBookStoreModulithWipApplication {

    public static void main(String[] args) {
        SpringApplication.from(BookStoreModulithWipApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
