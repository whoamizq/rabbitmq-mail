package com.whoamizq.rabbitmqmail.controller;

import com.google.common.collect.Lists;
import com.whoamizq.rabbitmqmail.annotation.AccessLimit;
import com.whoamizq.rabbitmqmail.annotation.ApiIdempotence;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.pojo.Mail;
import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.TestService;
import com.whoamizq.rabbitmqmail.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * @author: whoamizq
     * @description: 发送邮件
     * @date: 10:04 2020/10/20
     * @param: [mail, errors]
     * @return: com.whoamizq.rabbitmqmail.common.ServerResponse
     **/
    @PostMapping("send")
    public ServerResponse sendMail(@Validated Mail mail, Errors errors){
        if (errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }

        return testService.send(mail);
    }

    /**
     * @author: whoamizq
     * @description: 测试当线程批量执行操作时间
     * @date: 11:05 2020/10/20
     * @param: [size]
     * @return: com.whoamizq.rabbitmqmail.common.ServerResponse
     **/
    @PostMapping("single")
    public ServerResponse single(int size){
        List<User> list = Lists.newArrayList();

        for (int i=0; i<size; i++){
            String str = RandomUtil.UUID32();
            User user = User.builder().username(str).password(str).build();
            list.add(user);
        }

        long startTime = System.nanoTime();
        log.info("batch insert costs: {} ms",(System.nanoTime()-startTime)/1000000);

        return ServerResponse.success();
    }
}
