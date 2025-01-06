package com.zjun.myblog.home.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/2 18:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReqVO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
