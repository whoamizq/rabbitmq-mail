package com.whoamizq.rabbitmqmail.mapper;

import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.batch.BatchProcessMapper;

import java.util.List;

public interface UserMapper extends BatchProcessMapper<User> {
    void insert(User user);

    void update(User user);

    void delete(Integer id);

    List<User> selectAll();

    User selectOne(Integer id);

    User selectByUsernameAndPassword(String username, String password);
}
