package com.blog.blog.common.mapper;

import com.blog.blog.repository.dto.BoardDto;
import com.blog.blog.repository.dto.UserDto;
import com.blog.blog.repository.entity.Board;
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

    public static BoardDto boardMapper(Board board){
        ModelMapper modelMapper = new ModelMapper();
        BoardDto dto = new BoardDto();
        if(Objects.isNull(board)){
            return null;
        }
        modelMapper.map(board, dto);
        return dto;
    }
}
