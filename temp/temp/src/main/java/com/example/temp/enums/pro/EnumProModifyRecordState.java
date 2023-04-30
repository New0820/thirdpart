package com.example.temp.enums.pro;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumProModifyRecordState {

    SAVE_STORE("saveStore", "保存到仓库"),
    IMMEDIATELY_PUTAWAY("immediatelyPutaway", "立即上架"),
    SAVE_UPLOAD_NUM("saveUploadNum", "入库数量");


    private String code;

    private String msg;

    EnumProModifyRecordState(String code, String msg) {
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
