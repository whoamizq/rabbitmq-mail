package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.pojo.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    void delete(Integer id);

    List<User> getAll();

    User getOne(Integer id);
}
