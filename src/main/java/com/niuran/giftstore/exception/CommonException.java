package com.niuran.giftstore.exception;

import com.niuran.giftstore.enume.ResponseEnum;
import com.niuran.giftstore.util.EnumUtil;

/**
 * @Author MrD on 2018/7/9.
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -4541210687733091009L;
    private int code;
    private ResponseEnum responseEnum;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public CommonException(int code, String msg) {
        super(msg);
        this.code = code;
        this.responseEnum = EnumUtil.getEnumBycode(ResponseEnum.class,code);
    }

    public CommonException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.responseEnum = responseEnum;
    }

    public CommonException(ResponseEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.getCode();
        this.responseEnum = responseEnum;
    }

    public CommonException(String msg) {
        this(ResponseEnum.ERROR.getCode(), msg);
    }

    public static CommonException getInstance(String msg, Object... args) {
        for (Object object : args) {
            msg = msg.replaceFirst("\\{\\}", object.toString());
        }
        return new CommonException(msg);
    }

    public static CommonException getInstance(String msg) {
        return new CommonException(msg);
    }

}
