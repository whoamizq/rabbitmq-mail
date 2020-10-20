package com.whoamizq.rabbitmqmail.mq.consumer;

import com.rabbitmq.client.Channel;
import com.whoamizq.rabbitmqmail.exception.ServiceException;
import com.whoamizq.rabbitmqmail.mq.MessageHelper;
import com.whoamizq.rabbitmqmail.pojo.Mail;
import com.whoamizq.rabbitmqmail.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailConsumer {
    @Autowired
    private MailUtil mailUtil;

    public void consume(Message message, Channel channel){
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息：{}",mail.toString());

        if (!mailUtil.send(mail)){
            throw new ServiceException("发送邮件异常！");
        }
    }
}
