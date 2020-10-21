package com.whoamizq.rabbitmqmail.interceptor;

import com.whoamizq.rabbitmqmail.annotation.AccessLimit;
import com.whoamizq.rabbitmqmail.common.Constant;
import com.whoamizq.rabbitmqmail.common.ResponseCode;
import com.whoamizq.rabbitmqmail.exception.ServiceException;
import com.whoamizq.rabbitmqmail.util.IpUtil;
import com.whoamizq.rabbitmqmail.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: whoamizq
 * @description: 接口防刷限流拦截器
 * @date: 16:37 2020/10/20
 **/
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) { //不是控制器方法
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AccessLimit annotaction = method.getAnnotation(AccessLimit.class);
        if (annotaction != null) {
            check(annotaction, request);
        }
        return true;
    }

    private void check(AccessLimit annotaction, HttpServletRequest request) {
        int maxCount = annotaction.maxCount();
        int seconds = annotaction.seconds();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constant.Redis.ACCESS_LIMIT_PREFIX)
                .append(IpUtil.getIpAddress(request)).append(request.getRequestURI());
        String key = stringBuilder.toString();

        if (!jedisUtil.exists(key)) {
            jedisUtil.set(key, String.valueOf(1), seconds);
        } else {
            int count = Integer.valueOf(jedisUtil.get(key));
            if (count < maxCount){
                Long ttl = jedisUtil.ttl(key);
                if (ttl < 0){
                    jedisUtil.set(key, String.valueOf(1), seconds);
                }else {
                    jedisUtil.set(key, String.valueOf(++count), ttl.intValue());
                }
            }else {
                throw new ServiceException(ResponseCode.ACCESS_LIMIT.getMsg());// 请求频繁
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
