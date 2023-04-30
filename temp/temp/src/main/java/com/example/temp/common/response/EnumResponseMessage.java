package com.example.temp.common.response;

/**
 * 返回信息状态码枚举
 *
 * 10000-19999 系统
 *
 * 20000-29999 店铺
 *
 * 30000-39999 商品
 *
 * 40000-49999 订单
 */
public enum EnumResponseMessage {


    SUCCESS("10000", "success", "返回成功"),

    TOKEN_ERROR("11000", "token信息失效", "token信息失效"),

    SHOP_NOT_VIP("20000", "不是会员店铺", "不是会员店铺"),

    SHOP_NOT_VIP_PRODUCT_THIRTY("29000", "不是会员店铺，商品不能大于30件", "不是会员店铺，商品不能大于30件");


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
