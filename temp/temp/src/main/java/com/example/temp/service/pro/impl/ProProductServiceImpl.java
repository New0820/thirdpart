package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.constant.ConstantRedisKey;
import com.example.temp.param.ParamSaveProduct;
import com.example.temp.param.ParamUpdateProduct;
import com.example.temp.entity.pro.ProProduct;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.service.pro.ProProductService;
import com.example.temp.util.LocalUtils;
import com.example.temp.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 产品表;该表和(pro_detail)一对一关系 serverImplProDetailMapper
 * ProProductMapper
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:17
 */
@Service
public class ProProductServiceImpl extends ServiceImpl<ProProductMapper, ProProduct> implements ProProductService {

    @Resource
    private ProProductMapper proProductMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 添加商品
     *
     * @param param
     */
    @Override
    public void saveProduct(ParamSaveProduct param) {
        if (!isUpload(param.getShopId())) {
            //大于30件商品。不能入库
            //EnumResponseMessage.SHOP_NOT_VIP_PRODUCT_THIRTY;
        }
        //调用公共方法判断

    }


    /**
     * 更新商品
     *
     * @param param
     */
    @Override
    public void updateProduct(ParamUpdateProduct param) {
        ProProduct proProduct = getProductById(Integer.parseInt(param.getProId()));
        if (isUpload(param.getShopId())) {
            //todo 当前店铺
        }
    }

    /**
     * 判断店铺是否为30件商品 会员店铺
     *
     * @param shopId
     * @return
     */
    public Boolean isUpload(Integer shopId) {
        String memberState = redisUtil.get(ConstantRedisKey.getMemberStateRedisKeyByShopId(shopId));
        Integer productNum = 0;
        if (!(ConstantCommon.ZERO + ConstantCommon.ONE).contains(memberState)) {
            LambdaQueryWrapper<ProProduct> query = new LambdaQueryWrapper<>();
            query.eq(ProProduct::getFkShpShopId, shopId);
            query.between(ProProduct::getFkProStateCode, ConstantNums.TEN, ConstantNums.TWENTY_NINE);
            productNum = proProductMapper.selectCount(query);
        }
        return productNum >= ConstantNums.THIRTY;
    }


    /**
     * 根据商品id获取详情信息
     *
     * @param proId
     * @return
     */
    public ProProduct getProductById(Integer proId) {
        ProProduct proProduct = proProductMapper.selectById(proId);
        if (LocalUtils.isEmptyAndNull(proProduct)) {
            //todo "提示商品不存在";
        }
        return proProduct;
    }
}
