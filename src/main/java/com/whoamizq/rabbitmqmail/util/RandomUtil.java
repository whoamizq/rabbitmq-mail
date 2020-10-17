package com.whoamizq.rabbitmqmail.util;

import java.util.UUID;

/**
 * @author: whoamizq
 * @description: 生成UUID工具类
 * @date: 17:36 2020/10/17
 **/
public class RandomUtil {

    public static String UUID32(){
        String str = UUID.randomUUID().toString();
        return str.replaceAll("-","");
    }

    public static String UUID36(){
        return UUID.randomUUID().toString();
    }

}
