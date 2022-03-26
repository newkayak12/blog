package com.blog.blog;

import com.blog.blog.common.constants.Constants;
import com.blog.blog.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class BlogApplicationTests {
    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
    }

    @Test
    void signUpTest(){
        Map<String,Object> data = new HashMap<>();
        data.put("userId", "TEST2@test.com");
        data.put("userPassword", "qwer1234");
        data.put("userNickname", "TEST1");
        log.warn("Result :{}", userController.signUp(data));
    }

    @Test
    void signInTest(){
        log.warn("Result? : {}", userController.signIn("TEST1@test.com", "qwer1234"));
    }

    @Test
    void signOutTest(){
        log.warn("Result : {}", userController.signOut());
    }

    @Test
    void changePasswordTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Map<String,Object> data = new HashMap<>();
        data.put("userPassword", "qwer1234");
        data.put("newUserPassword", "qwer1234!");
        log.warn("Result : {}", userController.changePassword(token, data));
    }

    @Test
    void changeNickname(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Map<String,Object> data = new HashMap<>();
        data.put("userNickname", "test2");
        log.warn("Result : {}", userController.changeNickname(token, data));
    }

}
