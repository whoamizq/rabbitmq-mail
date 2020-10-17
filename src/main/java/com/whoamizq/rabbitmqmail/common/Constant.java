package com.whoamizq.rabbitmqmail.common;

/**
 * @author: whoamizq
 * @description: 全局通用常量类
 * @date: 17:23 2020/10/17
 **/
public class Constant {
    public static final int MAX_SIZE_PER_TIME = 1000;

    public interface Redis{
        String OK = "OK";
        Integer EXPIRE_TIME_MINUTE = 60; // 过期时间，60s，一分钟
        Integer EXPIRE_TIME_HOUR = 60 * 60; // 一小时
        Integer EXPIRE_TIME_DAY = 60 * 60 * 24; // 一天
        String TOKEN_PREFIX = "token:"; // token前缀
        String MSG_CONSUMER_PREFIX = "consumer:"; //
        String ACCESS_LIMIT_PREFIX = "accessLimit:"; //
    }

    public interface LogType{
        Integer LOGIN = 1;  // 登录
        Integer LOGOUT = 2; // 登出
    }

    public interface MsgLogStatus{
        Integer DELIVERING = 0;      // 消息发送中
        Integer DELIVER_SUCCESS = 1; // 发送成功
        Integer DELIVER_FAIL = 2;    // 发送失败
        Integer CONSUMED_SUCCESS = 3;// 已消费
    }
}
