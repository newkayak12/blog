package com.blog.blog.service;

import com.blog.blog.common.authorizations.tokenManager.TokenManager;
import com.blog.blog.common.constants.Constants;
import com.blog.blog.common.exception.ServiceException;
import com.blog.blog.common.mapper.Mapper;
import com.blog.blog.repository.dto.UserDto;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@Transactional(rollbackOn = {Exception.class})
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenManager tokenManager;
    private final  UserRepository userRepository;

    /**
     * 사용자 가입
     * @param data
     * @return userData/Token
     */
    public Map<String,Object> signUp(Map<String, Object> data) throws ServiceException {
        String userId = (String) data.get("userId");
        String userPassword = (String) data.get("userPassword");
        String userNickname = (String) data.get("userNickname");
        /**
         * errorCheck
         */
        User user = userRepository.findUserByUserIdOrUserNickname(userId, userNickname);
        log.warn("USERCHECK? : {}", user);
        UserDto userDto = Mapper.userMapper(user);
        log.warn("!@#Condition1 {}", !Objects.isNull(userDto) && userDto.getUserId().equals(userId));
        if(!Objects.isNull(userDto) && userDto.getUserId().equals(userId)){
            throw new ServiceException("이미 존재하는 사용자입니다.");
        }
        log.warn("!@#Condition2 {}", !Objects.isNull(userDto) && userDto.getUserNickname().equals(userNickname));
        if(!Objects.isNull(userDto) && userDto.getUserNickname().equals(userNickname)){
            throw new ServiceException("이미 존재하는 닉네임입니다.");
        }

        user = User.builder()
                .userId(userId)
                .userPassword(bCryptPasswordEncoder.encode(userPassword))
                .userNickname(userNickname)
                .userRegDate(new Date())
                .userLastSignedDate(new Date())
                .build();

        userRepository.save(user);

        return tokenMaker(user);
    }

    /**
     * 로그인
     * @param userId
     * @param userPassword
     * @return
     * @throws ServiceException
     */
    public Map<String, Object> signIn(String userId, String userPassword) throws ServiceException {
        User user = userRepository.findUserByUserId(userId);
        UserDto userDto = Mapper.userMapper(user);
        log.warn("????? {}", user);
        if(Objects.isNull(userDto)){
            throw new ServiceException("아이디 혹은 비밀번호가 틀렸습니다1.");
        }
        if(!bCryptPasswordEncoder.matches(userPassword, userDto.getUserPassword())){
            throw  new ServiceException("아이디 혹은 비밀번호가 틀렸습니다.2");
        }

        return tokenMaker(user);
    }

    /**
     * 로그 아웃
     * @return
     */
    public Map<String,Object> signOut(){
        Map<String,Object> result = new HashMap<>();
        result.put("token","");
        result.put("userData","");
        return result;
    }

    /**
     * 비밀번호 변경
     * @param data
     * @return
     * @throws ServiceException
     */
    public Map<String,Object> changePassword(Map<String, Object> data) throws ServiceException {
        Long userNo = Long.valueOf((Integer)data.get("userNo"));
        String userPassword = (String) data.get("userPassword");
        String newUserPassword = (String) data.get("newUserPassword");
        User user = userRepository.findUserByUserNo(userNo);
        UserDto userDto = Mapper.userMapper(user);

        if(!bCryptPasswordEncoder.matches(userPassword, userDto.getUserPassword())){
            throw new ServiceException("현재 비밀번호를 다시 확인해주세요");
        }
        if(bCryptPasswordEncoder.matches(newUserPassword,userDto.getUserPassword())){
            throw new ServiceException("현재 비밀번호와 변경할 비밀번호가 동일합니다");
        }

        user.setUserPassword(bCryptPasswordEncoder.encode(newUserPassword));
        userRepository.save(user);
        return tokenMaker(user);
    }

    /**
     * 닉네임 변경
     * @param data
     * @return
     * @throws ServiceException
     */
    public Map<String, Object> changeNickname(Map<String, Object> data) throws ServiceException {
        Long userNo = Long.valueOf((Integer) data.get("userNo"));
        String userNickname = (String) data.get("userNickname");
        User user = userRepository.findUserByUserNickname(userNickname);

        if(!Objects.isNull(user)&&!user.getUserNo().equals(userNo)){
            throw  new ServiceException("이미 존재하는 닉네임입니다.");
        }
        if(!Objects.isNull(user)&&user.getUserNo().equals(userNo)){
            throw  new ServiceException("현재 닉네임과 동일합니다.");
        }

        user = userRepository.findUserByUserNo(userNo);
        user.setUserNickname(userNickname);
        userRepository.save(user);

        return tokenMaker(user);
    }

    public Map<String,Object> tokenMaker(User user){

        UserDto userDto = Mapper.userMapper(user);
        userDto.setBoards(null);
        userDto.setUserPassword("");
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenManager.encrypt(userDto, Constants.SALT_VALUE));
        result.put("userData", userDto);

        return result;
    }



}
