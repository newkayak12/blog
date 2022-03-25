package com.blog.blog.controller;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.exception.ServiceException;
import com.blog.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseContainer signUp(@RequestBody Map<String,Object> data){
        Map<String,Object> result = null;
        try {
            result = userService.signUp(data);
        } catch (ServiceException e) {
            new ResponseContainer(500, e.getMessage(), null);
        }
        return new ResponseContainer(200, "가입에 성공하였습니다.", result);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ResponseContainer signIn(@RequestParam String userId, @RequestParam String userPassword){
        Map<String,Object> result = null;
        try {
            result = userService.signIn(userId, userPassword);
        } catch (ServiceException e) {
            return new ResponseContainer(500, "로그인에 실패했습니다.", null);
        }
        return new ResponseContainer(200, "로그인에 성공하였습니다.", result);
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public ResponseContainer signOut(){
        return new ResponseContainer(200, "", userService.signOut());
    }
}
