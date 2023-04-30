package com.example.temp.common.response;

/**
 * 返回信息状态码枚举
 */
public enum EnumResponseMessage {


    SUCCESS("10000", "success", "返回成功"),

    TOKEN_ERROR("20000", "token信息失效", "token信息失效");


    private String code;

    private String msg;

    private String description;


    EnumResponseMessage(String code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code + " " + msg + "" + description;
    }
}
