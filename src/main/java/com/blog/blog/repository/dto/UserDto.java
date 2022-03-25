package com.blog.blog.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private Long userNo;
    private String userId;
    private String userPassword;
    private String userNickname;
    private Instant userRegDate;
    private Instant userLastSignedDate;
    private List<BoardDto> boards;
}
