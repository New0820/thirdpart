package com.example.temp.enums.pro;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumProModifyRecordType {

    UPLOAD("upload", "入库"),
    UPDATE("update", "编辑");


    private String code;

    private String msg;

    EnumProModifyRecordType(String code, String msg) {
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
