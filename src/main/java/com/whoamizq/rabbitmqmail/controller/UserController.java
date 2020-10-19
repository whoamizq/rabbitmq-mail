package com.whoamizq.rabbitmqmail.controller;

import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: whoamizq
 * @description: 用户登录等
 * @date: 10:58 2020/10/19
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public String getAll() {
        List<User> users = userService.getAll();
        return users.toString();
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (StringUtils.isEmpty(user)) {
            return "not exists";
        }else{
            return user.toString();
        }
    }

    @PostMapping
    public String add(User user) {
        userService.add(user);
        return "nice";
    }

    @PutMapping
    public String update(User user) {
        userService.update(user);
        return "nice";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "nice";
    }

    /**
     * @author: whoamizq
     * @description: 用户登录
     * @date: 11:00 2020/10/19
     * @param: [username, password]
     * @return: com.whoamizq.rabbitmqmail.common.ServerResponse
     **/
    @PostMapping("login")
    public ServerResponse login(String username,String password){
        return userService.login(username,password);
    }
}
