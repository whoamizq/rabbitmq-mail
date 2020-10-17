package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.service.TokenService;
import com.whoamizq.rabbitmqmail.util.JedisUtil;
import com.whoamizq.rabbitmqmail.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32();
        StringBuilder token = new StringBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);
        // 设置token以及过期时间
        jedisUtil.set(token.toString(),token.toString(),Constant.Redis.EXPIRE_TIME_MINUTE);
        return ServerResponse.success(token.toString());
    }
}
