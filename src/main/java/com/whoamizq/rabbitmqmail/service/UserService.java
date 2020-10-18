package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.pojo.User;

public interface UserService {
    void add(User user);

    void update(User user);
}
