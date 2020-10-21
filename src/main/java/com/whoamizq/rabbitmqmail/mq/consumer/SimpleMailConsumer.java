package com.whoamizq.rabbitmqmail.mq.consumer;

import com.rabbitmq.client.Channel;
import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.config.RabbitConfig;
import com.whoamizq.rabbitmqmail.mq.MessageHelper;
import com.whoamizq.rabbitmqmail.pojo.Mail;
import com.whoamizq.rabbitmqmail.pojo.MsgLog;
import com.whoamizq.rabbitmqmail.service.MsgLogService;
import com.whoamizq.rabbitmqmail.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SimpleMailConsumer {
    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息：{}",mail.toString());

        String msgId = mail.getMsgId();
        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)){
            // 消费幂等性
            log.info("重复消费，msgId: {}",msgId);
            return;
        }
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        if (mailUtil.send(mail)){
            msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            channel.basicAck(tag, false);  // 消费确认
        }else {
            channel.basicNack(tag, false, true);
        }
    }
}
