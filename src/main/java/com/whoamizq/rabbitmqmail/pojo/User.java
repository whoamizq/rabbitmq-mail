package com.whoamizq.rabbitmqmail.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private String password2;
    private String password3;
    private String password4;
    private String password5;
    private String password6;
    private String password7;
    private String password8;
    private String password9;
    private String password10;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
