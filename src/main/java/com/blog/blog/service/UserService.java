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
        UserDto userDto = Mapper.userMapper(user);

        if(!Objects.isNull(userDto) && userDto.getUserId().equals(userId)){
            throw new ServiceException("이미 존재하는 사용자입니다.");
        }
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

    public Map<String, Object> signIn(String userId, String userPassword) throws ServiceException {
        User user = userRepository.findUserByUserId(userId);
        UserDto userDto = Mapper.userMapper(user);

        if(Objects.isNull(userDto)){
            throw new ServiceException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
        if(bCryptPasswordEncoder.matches(userPassword, userDto.getUserPassword())){
            throw  new ServiceException("아이디 혹은 비밀번호가 틀렸습니다.");
        }

        return tokenMaker(user);
    }

    public Map<String,Object> signOut(){
        Map<String,Object> result = new HashMap<>();
        result.put("token","");
        result.put("userData","");
        return result;
    }

    public Map<String,Object> tokenMaker(User user){

        UserDto userDto = Mapper.userMapper(user);
        userDto.setUserPassword("");
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenManager.encrypt(userDto, Constants.SALT_VALUE));
        result.put("userData", userDto);

        return result;
    }
}
