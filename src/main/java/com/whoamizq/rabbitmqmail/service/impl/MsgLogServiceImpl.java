package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.mapper.MsgLogMapper;
import com.whoamizq.rabbitmqmail.pojo.MsgLog;
import com.whoamizq.rabbitmqmail.service.MsgLogService;
import com.whoamizq.rabbitmqmail.util.JodaTimeUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgLogServiceImpl implements MsgLogService {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        // 加1分钟
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);
        msgLogMapper.updateTryCount(msgLog);
    }
}
