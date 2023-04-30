package com.example.temp.enums.pro;

/**
 * @author taoqimin
 * @date 2023年4月30日20:50:50
 * @apiNote
 */
public enum EnumProPriceName {

    INIT_PRICE("initPrice", "成本价"),
    TRADE_PRICE("tradePrice", "同行价"),
    AGENCY_PRICE("agencyPrice", "代理价"),
    SALE_PRICE("salePrice", "销售价");


    private String code;

    private String msg;

    EnumProPriceName(String code, String msg) {
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
