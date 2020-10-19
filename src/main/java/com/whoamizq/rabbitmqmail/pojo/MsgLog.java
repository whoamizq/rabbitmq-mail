package com.whoamizq.rabbitmqmail.pojo;

import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.util.JodaTimeUtil;
import com.whoamizq.rabbitmqmail.util.JsonUtil;
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

    public MsgLog(String msgId, Object msg, String exchange, String routingKey){
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
