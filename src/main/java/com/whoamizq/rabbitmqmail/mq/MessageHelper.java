package com.whoamizq.rabbitmqmail.mq;

import com.whoamizq.rabbitmqmail.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

import java.util.Objects;
/**
 * @author: whoamizq
 * @description: 对象与消息的互相转换
 * @date: 14:35 2020/10/19
 **/
public class MessageHelper {

    public static Message objToMsg(Object obj) {
        if (Objects.isNull(obj)){
            return null;
        }

        Message message = MessageBuilder.withBody(JsonUtil.objToStr(obj).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);  // 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);

        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz){
        if (null == message || null == clazz){
            return null;
        }
        String str = new String(message.getBody());
        T obj = JsonUtil.strToObj(str, clazz);
        return obj;
    }
}
