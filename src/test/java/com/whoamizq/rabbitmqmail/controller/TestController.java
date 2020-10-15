package com.whoamizq.rabbitmqmail.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testLogin() {
        for (int i = 0; i < 100; i++) {
            testRestTemplate.postForObject("/user/login?username={username}&password={password}", null,
                    String.class, "admin", "123456");
        }
    }
}
