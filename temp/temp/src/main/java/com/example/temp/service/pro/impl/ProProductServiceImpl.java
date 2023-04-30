package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.constant.ConstantRedisKey;
import com.example.temp.entity.pro.ProDetail;
import com.example.temp.entity.pro.ProProduct;
import com.example.temp.param.ParamSaveProduct;
import com.example.temp.param.ParamUpdateProduct;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.service.pro.ProDetailService;
import com.example.temp.service.pro.ProProductService;
import com.example.temp.util.LocalUtils;
import com.example.temp.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


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

    @Resource
    private ProDetailService proDetailService;

    /**
     * 添加商品
     *
     * @param param
     */
    @Override
    public void saveProduct(ParamSaveProduct param) {
        ParamUpdateProduct paramUpdateProduct = new ParamUpdateProduct();
        BeanUtils.copyProperties(paramUpdateProduct,param);
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(paramUpdateProduct);
    }


    /**
     * 更新商品
     *
     * @param param
     */
    @Override
    public void updateProduct(ParamUpdateProduct param) {
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(param);
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
            query.between(ProProduct::getFkProStateCode, ConstantNums.TEN,ConstantNums.TWENTY_NINE);
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

    /**
     * 对商品的基础参数进行封装和校验
     *
     * @param param
     * @return
     */
    public Map<String,Object> checkProductParam(ParamUpdateProduct param) {
        ProProduct proProduct = new ProProduct();
        ProDetail proDetail = new ProDetail();
        if (!LocalUtils.isEmptyAndNull(param.getBizId())) {
            //查询商品信息
            proProduct = getProductById(Integer.parseInt(param.getBizId()));
            //查询商品详情
            proDetail = proDetailService.getProDetailByProId(proProduct.getId());
        }
        //验证当前店铺是否已经添加了30件商品信息
        if (isUpload(param.getShopId())) {
            //todo 这个地方抛出提示
            return null;
        }
        //详情图片
        if (!LocalUtils.isEmptyAndNull(param.getProductList())) {
            proDetail.setProductImg(LocalUtils.getStringFromList(param.getProductList()));
        }
        //商品名称
        proProduct.setName(param.getName());
        //商品属性
        proProduct.setFkProAttributeCode(param.getAttributes());
        //商品分类
        proProduct.setFkProClassifyCode(param.getClassify());
        //商品首图
        proProduct.setSmallImg(param.getFirstImg());
        //商品描述
        proProduct.setDescription(LocalUtils.returnEmptyStringOrString(param.getDescription()));
        //商品备注
        proProduct.setRemark(LocalUtils.returnEmptyStringOrString(param.getRemark()));
        //成本价格
        proProduct.setInitPrice(new BigDecimal(param.getInitPrice()));
        //销售价格
        proProduct.setSalePrice(new BigDecimal(param.getSalePrice()));
        //同行价格
        proProduct.setTradePrice(new BigDecimal(param.getTradePrice()));
        //代理价格
        proProduct.setAgencyPrice(new BigDecimal(param.getAgencyPrice()));
        //商品库存默认为1
        proProduct.setTotalNum(ConstantNums.ONE);
        //独立编码
        proDetail.setUniqueCode(param.getUniqueCode());
        //视频图片
        proDetail.setVideoUrl(LocalUtils.returnEmptyStringOrString(param.getVideo()));

        Map<String,Object> map = new HashMap<>(10);
        map.put("proProduct",proProduct);
        map.put("proDetail",proDetail);
        return map;
    }
}
