package com.whoamizq.rabbitmqmail.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: whoamizq
 * @description: 在需要保证 接口幂等性 的Controller的方法上使用此注解
 * @date: 17:56 2020/10/19
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotence {
}
