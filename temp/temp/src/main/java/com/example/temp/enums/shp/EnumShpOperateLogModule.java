package com.example.temp.enums.shp;

/**
 * 编号记录流水类型枚举
 *
 * @author taoqimin
 * @date 2023年4月30日19:17:35
 */
public enum EnumShpOperateLogModule {

    //枚举
    SHOP("shop", "店铺", "店铺"),
    PROD("prod", "商品", "商品"),
    ORDER("order", "订单", "订单"),
    SERVICE("service", "维修保养", "维修保养"),
    STAFF("staff", "员工", "员工"),
    SALARY("salary", "薪资", "薪资"),
    BILL("bill", "记账本", "记账本"),
    LEAGUER_ALBUM("orderAlbum", "联营店铺", "联营店铺"),
    UNION("union", "线上展会", "线上展会"),
    DELIVER("deliver", "发货管理", "发货管理"),
    CHECK("check", "盘点", "盘点"),
    DELETE_HISTORY("deleteHistory", "回收站", "回收站"),
    DYNAMIC("dynamic", "商品位置", "商品位置"),
    TEMP("temp", "临时仓", "临时仓"),
    CONVEY("convey", "寄卖传送", "寄卖传送"),
    SYS_CONFIG("sysConfig", "设置中心", "设置中心"),
    ;

    /**
     * 代码(存入数据库)
     */
    private String code;
    /**
     * 代码名称(显示作用)
     */
    private String name;

    /**
     * 代码说明(对代码进行详细的补充说明)
     */
    private String description;


    EnumShpOperateLogModule(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code + " " + name + "" + description;
    }
}
