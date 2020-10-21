package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.pojo.MsgLog;

public interface MsgLogService {

    MsgLog selectByMsgId(String msgId);

    void updateStatus(String msgId, Integer status);
}
