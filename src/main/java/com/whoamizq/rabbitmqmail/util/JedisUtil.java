package com.whoamizq.rabbitmqmail.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: whoamizq
 * @description: jedis工具类
 * @date: 17:47 2020/10/17
 **/
@Component
@Slf4j
public class JedisUtil {

    @Autowired(required = false)
    private JedisPool jedisPool;

    private Jedis getJedis(){
        return jedisPool.getResource();
    }

    private void close(Jedis jedis){
        if (null != jedis){
            jedis.close();
        }
    }

    /**
     * @author: whoamizq
     * @description: 设值  ，expireTime过期时间
     * @date: 17:47 2020/10/17
     * @param: [key, value, expireTime]
     * @return: java.lang.String
     **/
    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try{
            jedis = getJedis();
            return jedis.setex(key,expireTime,value);
        }catch (Exception e){
            log.error("set key:{},value:{},expireTime:{},error:{}",key,value,expireTime,e);
            return null;
        }finally {
            close(jedis);
        }
    }

}
