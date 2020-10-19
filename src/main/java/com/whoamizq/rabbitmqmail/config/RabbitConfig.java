package com.whoamizq.rabbitmqmail.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Slf4j
public class RabbitConfig {
    // 登录日志
    public static final String LOGIN_LOG_EXCHANGE_NAME = "login.log.queue";
    public static final String LOGIN_LOG_ROUTING_KEY_NAME = "login.log.exchange";
}
