package com.whoamizq.rabbitmqmail.mapper;

import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.batch.BatchProcessMapper;

public interface UserMapper extends BatchProcessMapper<User> {
    void insert(User user);

    void update(User user);
}
