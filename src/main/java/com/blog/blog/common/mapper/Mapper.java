package com.blog.blog.common.mapper;

import com.blog.blog.repository.dto.UserDto;
import com.blog.blog.repository.entity.User;
import org.modelmapper.ModelMapper;

import java.util.Objects;

public class Mapper {

    public static UserDto userMapper(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserDto dto = new UserDto();
        if(Objects.isNull(user)){
            return null;
        }
        modelMapper.map(user, dto);
        return dto;
    }
}
