package com.whoamizq.rabbitmqmail.interceptor;

import com.whoamizq.rabbitmqmail.annotation.ApiIdempotence;
import com.whoamizq.rabbitmqmail.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: whoamizq
 * @description: 接口幂等性拦截器
 * @date: 14:25 2020/10/20
 **/
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        ApiIdempotence apiIdempotence = method.getAnnotation(ApiIdempotence.class);
        if (apiIdempotence != null){
            // 幂等性校验，校验通过则放行，校验失败则抛出异常，并通过统一异常处理返回
            check(request);
        }
        return true;
    }

    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
