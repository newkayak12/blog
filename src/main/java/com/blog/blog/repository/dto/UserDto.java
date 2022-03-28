package com.blog.blog.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private Long userNo;
    private String userId;
    @JsonIgnore
    private String userPassword;
    private String userNickname;
    private Date userRegDate;
    private Date userLastSignedDate;
    @JsonIgnore
    private List<BoardDto> boards;
}
