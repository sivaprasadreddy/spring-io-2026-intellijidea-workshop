package dev.sivalabs.bookstore.domain.service;

import java.util.List;

public interface EmailService {

    void send(String to, String subject, String content);

    void send(List<String> to, String subject, String content);

}