package com.whoamizq.rabbitmqmail.service;

import com.whoamizq.rabbitmqmail.pojo.MsgLog;

import java.util.Date;
import java.util.List;

public interface MsgLogService {

    MsgLog selectByMsgId(String msgId);

    void updateStatus(String msgId, Integer status);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(String msgId, Date nextTryTime);
}
