package com.whoamizq.rabbitmqmail.controller;

import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String add(User user){
        userService.add(user);
        return "nice";
    }
}
