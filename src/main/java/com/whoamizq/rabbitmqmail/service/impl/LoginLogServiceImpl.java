package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.mapper.LoginLogMapper;
import com.whoamizq.rabbitmqmail.pojo.LoginLog;
import com.whoamizq.rabbitmqmail.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void insert(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
    }
}
