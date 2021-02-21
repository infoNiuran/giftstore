package com.niuran.giftstore.response.base;

/**
 * @Author MrD on 2018/7/6.
 * 状态码返回
 */

public class CodeResponse {
    private int retCode;
    private String message;

    public CodeResponse(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
