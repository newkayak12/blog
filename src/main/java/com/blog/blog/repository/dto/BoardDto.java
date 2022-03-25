package com.blog.blog.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BoardDto implements Serializable {
    private  Long boardNo;
    private  UserDto userNo;
    private  String boardTitle;
    private  String boardContent;
    private  Instant boardWrittenDate;
    private  Instant boardUpdatedDate;

}
