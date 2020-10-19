package com.whoamizq.rabbitmqmail.exception;

import lombok.*;

/**
 * @author: whoamizq
 * @description: 定义全局异常---业务逻辑异常
 * @date: 16:36 2020/10/19
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ServiceException extends RuntimeException{
    private String code;
    private String msg;

    public ServiceException(String msg){
        this.msg = msg;
    }
}
