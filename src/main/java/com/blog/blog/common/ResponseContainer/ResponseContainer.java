package com.blog.blog.common.ResponseContainer;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ResponseContainer {
    private int code;
    private String msg;
    private Object data;
}
