package com.example.temp.mapper.shp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.temp.entity.shp.ShpUserShopRef;
import org.apache.ibatis.annotations.Mapper;


/**
 * 店铺--用户与店铺对应关系(多对多) dao
 *
 * @author taoqimin
 * @Date 2023-04-30 18:33:06
 */
@Mapper
public interface ShpUserShopRefMapper extends BaseMapper<ShpUserShopRef> {


}
