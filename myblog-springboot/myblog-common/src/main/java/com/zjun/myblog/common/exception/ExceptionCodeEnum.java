package com.zjun.myblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/5 22:07
 **/
@Getter
@AllArgsConstructor
public enum ExceptionCodeEnum {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),

    // ----------- 参数错误 -----------
    PARAM_NOT_VALID("10001", "参数错误"),

    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),

    // ----------- 登录失败状态码 -----------
    LOGIN_FAIL("20000", "登录失败"),

    // ----------- 用户名或密码错误状态码 -----------
    USERNAME_OR_PWD_ERROR("20001", "用户名或密码错误"),

    // ----------- 未授权状态码 -----------
    UNAUTHORIZED("20002", "无访问权限，请先登录！"),

    // ----------- 用户不存在 -----------
    USERNAME_NOT_FOUND("20003", "该用户不存在"),

    // ----------- 文章分类已存在 -----------
    CATEGORY_NAME_IS_EXISTED("20005", "该分类已存在，请勿重复添加！"),

    // ----------- 文件上传失败 -----------
    FILE_UPLOAD_FAILED("20008", "文件上传失败！"),

    // ----------- 提交的分类不存在 ----------
    CATEGORY_NOT_EXISTED("20009", "提交的分类不存在！"),

    // ----------- 文章不存在 ----------
    ARTICLE_NOT_FOUND("20010", "该文章不存在！"),
    ;

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

}
