package com.whoamizq.rabbitmqmail.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: whoamizq
 * @description: 在需要保证 接口防刷限流的 Controller 的方法上使用此注解
 * @date: 9:58 2020/10/20
 **/
@Target({
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    int maxCount();  // 最大访问次数

    int seconds();   // 固定时间，单位：s
}
