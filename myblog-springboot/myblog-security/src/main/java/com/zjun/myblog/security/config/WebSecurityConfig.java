package com.zjun.myblog.security.config;

import com.zjun.myblog.security.filter.JwtFilter;
import com.zjun.myblog.security.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/4 23:18
 **/
@Configuration
@EnableWebSecurity
@ComponentScan({"com.zjun.myblog.security.*"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthenticationSecurityConfig loginAuthenticationSecurityConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .formLogin().disable()
                .authenticationProvider(daoAuthenticationProvider())
                .apply(loginAuthenticationSecurityConfig)
                .and()
                .authorizeHttpRequests()
                .mvcMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint)
                .and()
                // .exceptionHandling().accessDeniedHandler(deniedHandler)
                // .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        // 直接使用 DaoAuthenticationProvider, 它是 Spring Security 提供的默认的身份验证提供者之一
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置 userDetailService，用于获取用户的详细信息
        provider.setUserDetailsService(userDetailsService);
        // 设置解码器
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

}
