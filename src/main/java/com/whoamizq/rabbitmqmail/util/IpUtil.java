package com.whoamizq.rabbitmqmail.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    /**
     * @author: whoamizq
     * @description: 获取用户真实ip地址
     * @date: 11:05 2020/10/21
     * @param: [request]
     * @return: java.lang.String
     **/
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");

        if (null==ip || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (null==ip || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (null==ip || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (null==ip || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (null==ip || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
