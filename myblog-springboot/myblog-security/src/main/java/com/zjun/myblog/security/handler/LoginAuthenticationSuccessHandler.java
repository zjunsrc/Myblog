package com.zjun.myblog.security.handler;

import com.zjun.myblog.common.response.Response;
import com.zjun.myblog.security.model.LoginRspVO;
import com.zjun.myblog.security.utils.HttpResponseUtils;
import com.zjun.myblog.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/6 10:56
 **/
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，这里是获取用户的用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 Token
        String username = userDetails.getUsername();
        String token = jwtUtils.generateToken(username);

        // 返回 Token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();
        HttpResponseUtils.ok(response, Response.success(loginRspVO));
    }
}
