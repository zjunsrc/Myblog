package com.zjun.myblog.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/5 19:22
 **/
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
