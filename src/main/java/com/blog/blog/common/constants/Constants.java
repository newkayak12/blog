package com.blog.blog.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String SALT_VALUE = "";
    public static String TEST_USER_ID = "";
    public static String TEST_USER_PASSWORD = "";
    public static String TEST_USER_NICKNAME = "";
    @Value("${CONSTANTS.SALT}")
    public void setSaltValue(String saltValue) {
        SALT_VALUE = saltValue;
    }
    @Value("${CONSTANTS.TEST_USER_ID}")
    public static void setTestUserId(String testUserId) {
        TEST_USER_ID = testUserId;
    }
    @Value("${CONSTANTS.TEST_USER_PASSWORD}")
    public static void setTestUserPassword(String testUserPassword) {
        TEST_USER_PASSWORD = testUserPassword;
    }
    @Value("${CONSTANTS.TEST_USER_NICKNAME}")
    public static void setTestUserNickname(String testUserNickname) {
        TEST_USER_NICKNAME = testUserNickname;
    }
}
