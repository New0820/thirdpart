package com.example.temp.service.shp;

import com.example.temp.entity.shp.ShpUserShopRef;

/**
 * 店铺--用户与店铺对应关系(多对多) service
 *
 * @author taoqimin
 * @Date 2023-04-30 18:33:06
 */
public interface ShpUserShopRefService {


    /**
     * 根据店铺id和用户id获取用户在此店铺昵称
     *
     * @param shopId
     * @param userId
     * @return
     */
    ShpUserShopRef getByShopIdAndUserId(Integer shopId, Integer userId);
}
