package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.mapper.MsgLogMapper;
import com.whoamizq.rabbitmqmail.service.MsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgLogServiceImpl implements MsgLogService {
    @Autowired
    private MsgLogMapper msgLogMapper;
}
