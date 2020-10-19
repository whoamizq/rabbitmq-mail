package com.whoamizq.rabbitmqmail.exception;

import com.whoamizq.rabbitmqmail.common.ResponseCode;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: whoamizq
 * @description: 全局异常处理
 * @date: 16:39 2020/10/19
 **/
@ControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ServerResponse serviceExceptionHandler(ServiceException serviceException) {
        return ServerResponse.error(serviceException.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ServerResponse exceptionHandler(Exception e) {
        log.error("Exception: ", e);// 服务器异常
        return ServerResponse.error(ResponseCode.SERVER_ERROR.getMsg());
    }
}
