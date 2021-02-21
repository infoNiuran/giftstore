package com.niuran.giftstore.enume;

import com.niuran.giftstore.enume.CommonEnum;

/**
 * @Author MrD on 2018/7/6.
 */
public enum ResponseEnum implements CommonEnum {
    SUCCESS(200,"请求成功","网络请求成功"),
    ERROR_USER_NOT_EXIST(40100, "用户不存在","当前用户不存在"),
    ERROR_TOKEN_TIMEOUT(40101, "token失效","登录信息过期，请重新登录"),
    ERROR_TOKEN(40102, "token错误","登录信息错误，请重新登录"),
    EMPTY_TOKEN(40103, "token不存在","登录信息错误，请重新登录"),
    METHOD_NOT_ALLOW(40500,"请求失败","请求方法不支持"),
    ERROR(500,"请求失败","服务器异常"),
    ERROR_CODE_NOT_EXIST(500, "验证码无效","验证码无效，请重新获取"),
    ERROR_USER_NO_REPUTATION(500, "用户信誉度过低","当前用户信誉度小于等于0"),
    ERROR_USER_FORBIDDEN(500, "用户被禁用","当前用户已被禁用"),
    ERROR_USER_ACCOUNT_NOT_EXIST(500, "当前用户账户信息不存在","当前用户账户信息不存在"),
    ERROR_USER_ACCOUNT_EXISTED(500, "账户已注册","当前用户账户信息已注册存在"),
    ERROR_USER_ACCOUNT_FORBIDDEN(403, "账户被禁用","当前用户账户被禁用"),
    ERROR_USER_ACCOUNT_NO_RESUME(405, "没有简历","当前用户未发布简历"),
    ERROR_USER_ACCOUNT_EXPIRED(500, "账户已过期","当前用户账户已过期"),
    ERROR_USER_NO_COM_AUTH(500, "用户未进行企业认证","当前用户未进行企业认证"),
    ERROR_USER_NO_PER_AUTH(500, "用户未进行个人","当前用户未进行个人认证"),
    ERROR_USER_PER_AUTH_EXISTED(500, "已存在认证信息","用户正在审核，请耐心等待"),
    ERROR_STATUS_NOT_EXIST(40600, "状态值不存在","当前状态值不存在"),
    ERROR_USER_STATUS_CLOSE(40700, "用户已被禁用","当前用户已被禁用"),

    REGISTER_ERROR_USER_FORBIDDEN(501,"用户已被禁用","当前用户已被禁止注册"),
    REGISTER_ERROR_USER_EXISTED(503,"用户已存在","当前用户已存在"),
    ERROR_ACCOUNT_ERROR(500, "账户密码错误","账户密码错误"),
    ERROR_NO_PERMISSION(500, "无权操作","无权进行此操作"),

    ERROR_WITHDRAW_SYSTEM(500,"请求失败","提现错误，参见系统具体错误原因"),
    ERROR_WITHDRAW_SIGN(500,"签名错误", "签名错误")
    ;

    private int code; //状态
    private String message; // 信息
    private String desc;    //详细描述

    ResponseEnum(int code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
