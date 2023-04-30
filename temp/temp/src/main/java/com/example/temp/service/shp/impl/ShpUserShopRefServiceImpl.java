package com.example.temp.service.shp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.entity.shp.ShpUserShopRef;
import com.example.temp.mapper.shp.ShpUserShopRefMapper;
import com.example.temp.service.shp.ShpUserShopRefService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 店铺--用户与店铺对应关系(多对多) serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 18:33:06
 */
@Service
public class ShpUserShopRefServiceImpl extends ServiceImpl<ShpUserShopRefMapper, ShpUserShopRef> implements ShpUserShopRefService {

    @Resource
    private ShpUserShopRefMapper shpUserShopRefMapper;

    /**
     * 根据店铺id和用户id获取用户在此店铺昵称
     *
     * @param shopId
     * @param userId
     * @return
     */
    @Override
    public ShpUserShopRef getByShopIdAndUserId(Integer shopId, Integer userId) {
        LambdaQueryWrapper<ShpUserShopRef> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShpUserShopRef::getFkShpShopId, shopId);
        queryWrapper.eq(ShpUserShopRef::getFkShpUserId, userId);
        queryWrapper.eq(ShpUserShopRef::getDel, ConstantCommon.ZERO);
        queryWrapper.orderByDesc(ShpUserShopRef::getId).last("limit 1");
        ShpUserShopRef shpUserShopRef = shpUserShopRefMapper.selectOne(queryWrapper);
        if (LocalUtils.isEmptyAndNull(shpUserShopRef)) {
            // TODO: 2023/4/30  抛异常。用户信息未找到
        }
        return shpUserShopRef;
    }
}
