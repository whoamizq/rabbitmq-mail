package com.whoamizq.rabbitmqmail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author whoamizq
 * @description 访问首页
 * @date 9:08 2020/10/15
 **/
@Controller
public class ViewController {
    @GetMapping(value = "home")
    public String index(){
        return "index";
    }
}
