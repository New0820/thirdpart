package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.constant.ConstantRedisKey;
import com.example.temp.entity.pro.*;
import com.example.temp.enums.shp.EnumShpOperateLogModule;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.param.ParamSaveProduct;
import com.example.temp.param.ParamShpOperateLogSave;
import com.example.temp.param.ParamUpdateProduct;
import com.example.temp.service.pro.*;
import com.example.temp.service.shp.ShpOperateLogService;
import com.example.temp.util.LocalUtils;
import com.example.temp.util.RedisUtil;
import com.example.temp.util.ServicesUtil;
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

    @Resource
    private ShpOperateLogService shpOperateLogService;

    @Resource
    private ServicesUtil servicesUtil;

    @Resource
    private ProBrandService proBrandService;

    @Resource
    private ProBrandSeriesService proBrandSeriesService;

    @Resource
    private ProBrandModelService proBrandModelService;

    /**
     * 添加商品
     *
     * @param param
     */
    @Override
    public void saveProduct(ParamSaveProduct param) {
        ParamUpdateProduct paramUpdateProduct = new ParamUpdateProduct();
        BeanUtils.copyProperties(paramUpdateProduct, param);
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(paramUpdateProduct);
        ProProduct proProduct = (ProProduct) map.get("proProduct");
        save(proProduct);
        ProDetail proDetail = (ProDetail) map.get("proDetail");
        proDetail.setFkProProductId(proProduct.getId());
        //添加商品详情
        proDetailService.saveProDetail(proDetail);
        //添加店铺操作日志
        ParamShpOperateLogSave shpOperateLogSave = jointShpOperateLog(param);
        shpOperateLogService.saveShpOperateLog(shpOperateLogSave);
    }

    /**
     * 拼接店铺操作日志请求参数
     *
     * @param param
     * @return
     */
    public ParamShpOperateLogSave jointShpOperateLog(ParamSaveProduct param) {
        ParamShpOperateLogSave shpOperateLogSave = new ParamShpOperateLogSave();
        shpOperateLogSave.setShopId(param.getShopId());
        shpOperateLogSave.setOperateUserId(param.getUserId());
        shpOperateLogSave.setModuleName(EnumShpOperateLogModule.PROD.getName());
        shpOperateLogSave.setOperateName("商品入库");
        String str = "成本价:";
        String beforeValue = str + formatPrice(param.getInitPrice()) + ",同行价:" + formatPrice(param.getTradePrice())
                + ",代理价:" + formatPrice(param.getAgencyPrice()) + ",销售价:" + formatPrice(param.getSalePrice()) + ",商品属性:"
                + servicesUtil.getAttributeCn(ConstantNums.TEN.toString(), true);
        String prodName = param.getName() + "商品信息：" + beforeValue;
        shpOperateLogSave.setOperateContent(prodName);
        shpOperateLogSave.setProdId(param.getProId());
        shpOperateLogSave.setRequest(param.getRequest());
        return shpOperateLogSave;
    }

    public BigDecimal formatPrice(Object object) {
        try {
            return LocalUtils.formatPrice(LocalUtils.calcNumber(LocalUtils.isEmptyAndNull(object) ? 0 : object, "*", 0.01));
        } catch (Exception e) {
            // TODO: 2023/4/30 价格有问题
        }
        return new BigDecimal(0);
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

    /**
     * 对商品的基础参数进行封装和校验
     *
     * @param param
     * @return
     */
    public Map<String, Object> checkProductParam(ParamUpdateProduct param) {
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

        //获取品牌名称
        if (!LocalUtils.isEmptyAndNull(param.getBrandId())) {
            ProBrand proBrand = proBrandService.getProBrandById(Integer.parseInt(param.getBrandId()));
            proProduct.setFkProClassifySubName(proBrand.getEnName());
        }

        //如果品牌id为空，系列id不为空
        if (LocalUtils.isEmptyAndNull(param.getBrandId()) && !LocalUtils.isEmptyAndNull(param.getSeriesId())) {
            //todo 提示请选择品牌
        } else {
            //获取系列名称
            ProBrandSeries proBrandSeries = proBrandSeriesService.getProBrandSeriesByIdOrBrandId(Integer.parseInt(param.getSeriesId()), Integer.parseInt(param.getBrandId()));
            proProduct.setFkProSubSeriesName(proBrandSeries.getName());
        }

        //如果品牌和系列为空，型号不为空
        boolean flag = LocalUtils.isEmptyAndNull(param.getBrandId()) || LocalUtils.isEmptyAndNull(param.getSeriesId());
        if (flag && !LocalUtils.isEmptyAndNull(param.getModelId())) {
            //todo 提示请选择系列
        } else {
            ProBrandModel proBrandModel = proBrandModelService.getByBrandModelByIdOrSeriesId(Integer.parseInt(param.getSeriesId()), Integer.parseInt(param.getModelId()));
            proProduct.setFkProSeriesModelName(proBrandModel.getName());
        }

        //商品名称
        proProduct.setName(param.getName());
        //商品属性
        proProduct.setFkProAttributeCode(ConstantNums.TEN.toString());
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

        Map<String, Object> map = new HashMap<>(10);
        map.put("proProduct", proProduct);
        map.put("proDetail", proDetail);
        return map;
    }
}
