package com.zjun.myblog.home.controller;

import com.zjun.myblog.home.model.view.LoginReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * A placeholder Controller for Knife4j to generate /login API documentation.
 * </p>
 * <p>
 * Actual request handling logic is implented in the TODO
 * </p>
 *
 * @Author: zjun
 * @Date: 2025/1/2 18:03
 **/
@RestController
@Api(tags = "登录")
public class LoginController {
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public void login(@RequestBody @Validated LoginReqVO loginReqVO) {
        return;
    }
}
