package com.whoamizq.rabbitmqmail.controller;

import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * @author: whoamizq
     * @description: token获取
     * @date: 17:34 2020/10/17
     * @param: []
     * @return: com.whoamizq.rabbitmqmail.common.ServerResponse
     **/
    @GetMapping
    public ServerResponse token(){
        return tokenService.createToken();
    }

}
