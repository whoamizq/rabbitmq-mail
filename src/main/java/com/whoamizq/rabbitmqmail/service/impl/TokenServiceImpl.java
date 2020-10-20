package com.whoamizq.rabbitmqmail.service.impl;

import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.common.ResponseCode;
import com.whoamizq.rabbitmqmail.common.ServerResponse;
import com.whoamizq.rabbitmqmail.exception.ServiceException;
import com.whoamizq.rabbitmqmail.service.TokenService;
import com.whoamizq.rabbitmqmail.util.JedisUtil;
import com.whoamizq.rabbitmqmail.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

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

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)){   // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)){  // parameter中也不存在token
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedisUtil.exists(token)){ // 请勿重复操作
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedisUtil.del(token);
        if (del <= 0){
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
