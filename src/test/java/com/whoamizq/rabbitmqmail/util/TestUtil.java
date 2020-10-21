package com.whoamizq.rabbitmqmail.util;

import org.junit.jupiter.api.Test;

public class TestUtil {

    @Test
    public void test01(){
        String number = RandomUtil.generateDigitalStr(4);
        System.out.println(number);
    }

}
