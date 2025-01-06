package com.myblog.security.config;

import com.myblog.security.filter.LoginAuthenticationFilter;
import com.myblog.security.handler.LoginAuthenticationFailureHandler;
import com.myblog.security.handler.LoginAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/5 20:15
 **/
@Configuration
public class LoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 自定义的用于 JWT 身份验证的过滤器
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));

        // 设置登录认证对应的处理类（成功处理、失败处理）
        filter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);

        // // 直接使用 DaoAuthenticationProvider, 它是 Spring Security 提供的默认的身份验证提供者之一
        // DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // // 设置 userDetailService，用于获取用户的详细信息
        // provider.setUserDetailsService(userDetailsService);
        // // 设置加密算法
        // provider.setPasswordEncoder(passwordEncoder);
        // httpSecurity.authenticationProvider(provider);

        // 将这个过滤器添加到 UsernamePasswordAuthenticationFilter 之前执行
        System.out.println("httpSecurity = " + httpSecurity.getSharedObject(AuthenticationManager.class));
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

}
