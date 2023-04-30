package com.example.temp.service.pro.impl;

import com.example.temp.entity.param.ParamSaveProduct;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.service.pro.ProProductService;
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
        if (!(ConstantCommon.ZERO + ConstantCommon.ONE).contains(memberState)) {
            QueryWrapper<ProProduct> query = new QueryWrapper<>();
            query.eq("fk_shp_shop_id", shopId);
            query.ge("fk_pro_state_code", ConstantNums.TEN);
            query.le("fk_pro_state_code", ConstantNums.TWENTY_NINE);
            Integer productNum = proProductMapper.selectCount(query);
            if (productNum >= ConstantNums.THIRTY) {
                return false;
            }
        }
        return true;
    }


    /**
     * 根据商品id获取详情信息
     * @param proId
     * @return
     */
    public ProProduct getProductById(Integer proId){
        ProProduct proProduct = proProductMapper.selectById(proId);
        if (LocalUtils.isEmptyAndNull(proProduct)){
            //todo "提示商品不存在";
        }
        return proProduct;
    }
}
