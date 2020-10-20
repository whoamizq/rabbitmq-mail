package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);
}
