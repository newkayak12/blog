package com.blog.blog.controller;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.authorizations.annotation.Auth;
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

    /**
     * 회원 가입
     * @param data
     * @return
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseContainer signUp(@RequestBody Map<String,Object> data){
        Map<String,Object> result = null;
        try {
            result = userService.signUp(data);
        } catch (ServiceException e) {
          return  new ResponseContainer(500, e.getMessage(), null);
        }
        return new ResponseContainer(200, "가입에 성공하였습니다.", result);
    }

    /**
     * 로그인
     * @param userId
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ResponseContainer signIn(@RequestParam String userId, @RequestParam String userPassword){
        Map<String,Object> result = null;
        try {
            result = userService.signIn(userId, userPassword);
        } catch (ServiceException e) {
            return new ResponseContainer(500, e.getMessage(), null);
        }
        return new ResponseContainer(200, "로그인에 성공하였습니다.", result);
    }

    /**
     * 로그아웃
     * @return
     */
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public ResponseContainer signOut(){
        return new ResponseContainer(200, "", userService.signOut());
    }

    /**
     * 비밀번호 변경
     * @param authorization
     * @param data
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.PATCH)
    @Auth
    public ResponseContainer changePassword(@RequestHeader(value = "Authorization") Object authorization,  @RequestBody Map<String,Object> data){
        data.put("userNo",  ((Map<String,Object>) authorization).get("userNo"));
        Map<String, Object> result = null;
        try {
            result = userService.changePassword(data);
        } catch (ServiceException e) {
            return new ResponseContainer(500, e.getMessage(), null);
        }

        return new ResponseContainer(200, "비밀번호 변경에 성공했습니다.", result);
    }

    /**
     * 닉네임 변경
     * @param authorization
     * @param data
     * @return
     */
    @RequestMapping(value = "/changeNickname", method = RequestMethod.PATCH)
    @Auth
    public ResponseContainer changeNickname(@RequestHeader(value = "Authorization") Object authorization,  @RequestBody Map<String,Object> data){
        data.put("userNo",  ((Map<String,Object>) authorization).get("userNo"));
        Map<String,Object> result = null;
        try {
            result = userService.changeNickname(data);
        } catch (ServiceException e) {
            return new ResponseContainer(500, e.getMessage(),null);
        }
        return new ResponseContainer(200, "닉네임 변경에 성공했습니다.", result);
    }

}
