package com.whoamizq.rabbitmqmail.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    public ConfigUtil() {
    }

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

    public static void updateProperties(String key, String value){
        properties.setProperty(key,value);
    }
}
