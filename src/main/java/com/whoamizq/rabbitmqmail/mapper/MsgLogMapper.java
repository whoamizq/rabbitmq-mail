package com.whoamizq.rabbitmqmail.mapper;

import com.whoamizq.rabbitmqmail.pojo.MsgLog;
import com.whoamizq.rabbitmqmail.service.batch.BatchProcessMapper;

import java.util.List;

public interface MsgLogMapper extends BatchProcessMapper<MsgLog> {

    void insert(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);
}
