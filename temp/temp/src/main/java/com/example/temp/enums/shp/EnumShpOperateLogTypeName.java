package com.example.temp.enums.shp;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumShpOperateLogTypeName {

    PRO_UPLOAD("proUpload", "商品入库");


    private String code;

    private String msg;

    EnumShpOperateLogTypeName(String code, String msg) {
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
