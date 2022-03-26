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
        data.put("userId", Constants.TEST_USER_ID);
        data.put("userPassword", Constants.TEST_USER_PASSWORD);
        data.put("userNickname", Constants.TEST_USER_NICKNAME);
        log.warn("Result :{}", userController.signUp(data));
    }

    @Test
    void signInTest(){

    }

}
