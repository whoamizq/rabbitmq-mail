package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.common.ServerResponse;

public interface TestService {
    ServerResponse testIdempotence();
}
