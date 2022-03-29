package com.blog.blog;

import com.blog.blog.controller.BoardController;
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
    @Autowired
    BoardController boardController;

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
        log.warn("Result? : {}", userController.signIn("TEST2@test.com", "qwer1234"));
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

    @Test
    void writeTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        for(int i =0; i<10; i++){
            Map<String,Object> write = new HashMap<>();
            write.put("boardTitle", "TEST"+i);
            write.put("boardContent", "TEST_CONTENT_"+i);
            log.warn("Result : {}", boardController.writeBoard(token, write));
        }
    }

    @Test
    void modifyTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Map<String,Object> write = new HashMap<>();
        write.put("boardNo", 1);
        write.put("boardTitle", "TEST1");
        write.put("boardContent", "TEST_CONTENT_1");
        log.warn("Result : {}", boardController.modifyBoard(token, write));
    }

    @Test
    void fetchOneTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Integer boardNo = 1;
        log.warn("Result : {}", boardController.fetchOne(token, boardNo));
    }

    @Test
    void fetchListTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Integer page = 0;
        Integer limit = 10;
        String searchText="";
        log.warn("Result : {}", boardController.fetchList(token, page,limit,searchText));
    }

    @Test
    void deleteTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyTm8iOjgsInVzZXJOaWNrbmFtZSI6IlRFU1QxIiwidXNlcklkIjoiVEVTVDFAdGVzdC5jb20ifQ.Qq62Jgxn4J4qPKR8FYSPExhz5OuMvsxFcmAn0xhezuOrDCmR2F3gTHE9MyyEhE8RVzwyH3oU_WAmziQZve3frQ";
        Integer boardNo = 25;
        log.warn("Result : {}", boardController.deleteBoard(token,boardNo));
    }

}
