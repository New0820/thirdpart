package com.example.temp.constant;

/**
 * 内置常量
 *
 * @author taoqimin
 * @date 2023年4月30日16:26:22
 */
public class ConstantRedisKey {


    public static final String SHP_SHOP_ID = "shp:shopId:";

    private static String getShpShopByShopId(Integer shopId, String key) {
        return SHP_SHOP_ID + shopId + key;
    }

    /**
     * 会员状态: 0:非会员; 1:体验会员;2:正式会员;3:靓号会员
     *
     * @return
     */
    public static String getMemberStateRedisKeyByShopId(Integer shopId) {
        return getShpShopByShopId(shopId, ":member_state");
    }

}
