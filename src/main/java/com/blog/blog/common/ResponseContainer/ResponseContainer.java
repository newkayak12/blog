package com.blog.blog.common.ResponseContainer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ResponseContainer {
    private int code;
    private String msg;
    private Object data;
}
