package com.example.temp.enums.shp;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumShpSource {

    SHEBAOBAO("shebaobao", "奢宝宝");


    private String code;

    private String msg;

    EnumShpSource(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
