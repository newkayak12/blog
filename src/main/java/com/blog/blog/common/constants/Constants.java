package com.blog.blog.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String SALT_VALUE = "";
    @Value("${CONSTANTS.SALT}")
    public void setSaltValue(String saltValue) {
        SALT_VALUE = saltValue;
    }
}
