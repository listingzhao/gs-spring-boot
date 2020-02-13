package com.leyiju.enums;

public enum ResponseStatus {

    /* 成功状态码 10000 */
    SUCCESS(200, "成功"),

    NO_UNAUTHORIZED(401, "认证失败，请重新登录"),

    NO_PERMISSIONS(403, "暂无访问权限，请联系管理员"),
    /* 账号错误*/

    /*用户未登录*/
    ACCOUNT_NOT_LOGIN(10001, "用户未登录"),
    /*账号不存在或密码错误*/
    ACCOUNT_LOGIN_ERROR(10002, "账号不存在或密码错误"),
    /*账号已存在*/
    ACCOUNT_IS_EXISTENT(10003, "账号已经存在"),
    /*手机号已存在*/
    PHONE_IS_EXISTENT(10006, "手机号已绑定"),
    /*账号不存在*/
    ACCOUNT_NOT_EXIST(10004, "账号不存在"),
    /*手机号验证码错误*/
    ACCOUNT_IS_PHONECODE_ERROR(10005, "手机号验证码错误"),

    /* 参数错误*/
    /*参数不为空*/
    PARAMS_NOT_IS_BLANK(20001, "参数不为空");

    private Integer status;

    private String msg;

    ResponseStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
