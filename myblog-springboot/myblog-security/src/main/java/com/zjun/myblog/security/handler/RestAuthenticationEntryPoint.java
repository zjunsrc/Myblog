package com.zjun.myblog.security.handler;

import com.zjun.myblog.common.exception.ExceptionCodeEnum;
import com.zjun.myblog.common.response.Response;
import com.zjun.myblog.security.utils.HttpResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/7 10:15
 **/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {

        if (authException instanceof InsufficientAuthenticationException) {
            HttpResponseUtils.fail(response, HttpStatus.UNAUTHORIZED.value(), Response.fail(ExceptionCodeEnum.UNAUTHORIZED));
        }

        HttpResponseUtils.fail(response, HttpStatus.UNAUTHORIZED.value(), Response.fail(authException.getMessage()));
    }
}
