package com.whoamizq.rabbitmqmail.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Integer userId;
    private Integer type;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String msgId;  // 消息id
}
