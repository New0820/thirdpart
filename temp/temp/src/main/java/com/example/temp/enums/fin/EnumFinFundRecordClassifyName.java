package com.example.temp.enums.fin;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumFinFundRecordClassifyName {

    UPLOAD_RECORD("uploadRecord", "入库记录");


    private String code;

    private String msg;

    EnumFinFundRecordClassifyName(String code, String msg) {
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
