package com.whoamizq.rabbitmqmail.mapper;

import com.whoamizq.rabbitmqmail.pojo.User;
import com.whoamizq.rabbitmqmail.service.batch.BatchProcessMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BatchProcessMapper<User> {
    void insert(User user);

    void update(User user);

    void delete(Integer id);

    List<User> selectAll();

    User selectOne(Integer id);

    User selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    User selectByUsername(String username);
}
