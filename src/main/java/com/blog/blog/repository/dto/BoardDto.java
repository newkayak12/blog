package com.blog.blog.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class BoardDto implements Serializable {
    private  Long boardNo;
    private  UserDto userNo;
    private  String boardTitle;
    private  String boardContent;
    private  Date boardWrittenDate;
    private  Date boardUpdatedDate;

}
