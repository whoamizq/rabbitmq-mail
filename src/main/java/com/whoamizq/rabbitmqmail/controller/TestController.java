package com.whoamizq.rabbitmqmail.controller;

import com.whoamizq.rabbitmqmail.annotation.AccessLimit;
import com.whoamizq.rabbitmqmail.annotation.ApiIdempotence;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.pojo.Mail;
import com.whoamizq.rabbitmqmail.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * @author: whoamizq
     * @description: 测试幂等性
     * @date: 17:53 2020/10/19
     **/
    @ApiIdempotence
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence(){
        return testService.testIdempotence();
    }

    /**
     * @author: whoamizq
     * @description: 测试防刷限流
     * @date: 10:02 2020/10/20
     **/
    @AccessLimit(maxCount = 5, seconds = 5)
    @PostMapping("accessLimit")
    public ServerResponse accessLimit(){
        return testService.accessLimit();
    }

//    public ServerResponse sendMail(@Validated Mail mail, Errors errors){
//
//    }
}
