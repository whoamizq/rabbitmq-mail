package com.whoamizq.rabbitmqmail.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailUtil {
    @Value("${spring.mail.from}")
    private String from;
}
