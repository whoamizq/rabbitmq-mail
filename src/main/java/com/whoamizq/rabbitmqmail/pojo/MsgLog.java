package com.whoamizq.rabbitmqmail.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class MsgLog {
    private String msgId;
    private String msg;
    private String exchange;
    private String routingKey;
    private Integer status;
    private Integer tryCount;
    private Date nextTryTime;
    private Date createTime;
    private Date updateTime;
}
