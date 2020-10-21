package com.whoamizq.rabbitmqmail.mq.listenter;

import com.rabbitmq.client.Channel;
import com.whoamizq.rabbitmqmail.config.RabbitConfig;
import com.whoamizq.rabbitmqmail.mq.BaseConsumer;
import com.whoamizq.rabbitmqmail.mq.BaseConsumerProxy;
import com.whoamizq.rabbitmqmail.mq.consumer.MailConsumer;
import com.whoamizq.rabbitmqmail.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MailListener {
    @Autowired
    private MailConsumer mailConsumer;
    @Autowired
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy){
            proxy.consume(message,channel);
        }
    }
}
