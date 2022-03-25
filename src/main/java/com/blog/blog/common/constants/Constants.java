package com.blog.blog.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String SALT_VALUE;

    @Value("${CONSTCODE.SALT}")
    public static void setSaltValue(String saltValue) {
        SALT_VALUE = saltValue;
    }
}
