package com.whoamizq.rabbitmqmail.mq.consumer;

import com.rabbitmq.client.Channel;
import com.whoamizq.rabbitmqmail.mq.BaseConsumer;
import com.whoamizq.rabbitmqmail.mq.MessageHelper;
import com.whoamizq.rabbitmqmail.pojo.LoginLog;
import com.whoamizq.rabbitmqmail.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {
    @Autowired
    private LoginLogService loginLogService;

    @Override
    public void consume(Message message, Channel channel) throws IOException {
        log.info("收到消息：{}",message.toString());
        LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
        loginLogService.insert(loginLog);
    }
}
