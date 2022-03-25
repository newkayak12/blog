package com.blog.blog.common.ResponseContainer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseContainer {
    private int code;
    private String msg;
    private Object data;
}
