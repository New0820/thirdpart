package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.common.base.BaseResult;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.constant.ConstantRedisKey;
import com.example.temp.entity.pro.*;
import com.example.temp.enums.pro.EnumProState;
import com.example.temp.enums.pro.EnumProModifyRecordState;
import com.example.temp.enums.pro.EnumProModifyRecordType;
import com.example.temp.enums.fin.EnumFinShopRecordInoutType;
import com.example.temp.enums.shp.EnumShpOperateLogModule;
import com.example.temp.enums.shp.EnumShpSource;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.param.fin.ParamFundRecordSave;
import com.example.temp.param.pro.ParamProModifyRecordSave;
import com.example.temp.param.pro.ParamProductSave;
import com.example.temp.param.pro.ParamProductUpdate;
import com.example.temp.param.shp.ParamShpOperateLogSave;
import com.example.temp.service.fin.FinBillService;
import com.example.temp.service.pro.*;
import com.example.temp.service.shp.ShpOperateLogService;
import com.example.temp.util.LocalUtils;
import com.example.temp.util.RedisUtil;
import com.example.temp.util.ServicesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
@Slf4j
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

    @Resource
    private ProductEsService productEsService;

    @Resource
    private FinBillService finBillService;

    @Resource
    private ProModifyRecordService proModifyRecordService;

    /**
     * 添加商品
     *
     * @param param
     */
    @Override
    public void saveProduct(ParamProductSave param) {
        ParamProductUpdate paramUpdateProduct = new ParamProductUpdate();
        BeanUtils.copyProperties(paramUpdateProduct, param);
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(paramUpdateProduct);
        ProProduct proProduct = (ProProduct) map.get("proProduct");
        Date date = new Date();
        long autoNumber = 0L;
        try {
            //生成独立编码
            autoNumber = LocalUtils.calcNumber(proProduct.getId(), "+", 1000000).longValue();
        } catch (Exception e) {
            log.info("添加商品信息失败,请求参数:{},异常信息:{}", param, e.getMessage());
            //todo 系统维护升级中
        }
        proProduct.setInsertTime(date);
        proProduct.setUpdateTime(date);
        save(proProduct);
        ProDetail proDetail = (ProDetail) map.get("proDetail");
        proDetail.setInsertTime(date);
        proDetail.setUpdateTime(date);
        proDetail.setAutoNumber(autoNumber + "");
        proDetail.setFkProProductId(proProduct.getId());
        //添加商品详情
        proDetailService.saveProDetail(proDetail);
        //添加店铺操作日志
        ParamShpOperateLogSave shpOperateLogSave = jointShpOperateLog(param);
        shpOperateLogService.saveShpOperateLog(shpOperateLogSave);
        //添加账单记录
        ParamFundRecordSave paramFundRecordSave = jointFundRecordSave(param);
        finBillService.saveProductFundRecord(paramFundRecordSave);
        //商品记录信息
        proModifyRecordService.saveProModifyRecord(param);
        //添加商品数量记录信息
        proModifyRecordService.saveProModifyRecordNum(param);
        productEsService.jointProInfo(proProduct, proDetail, ConstantCommon.ES_SAVE);
    }

    /**
     * 拼接账单流水记录
     *
     * @param param
     * @return
     */
    public ParamFundRecordSave jointFundRecordSave(ParamProductSave param) {
        ParamFundRecordSave fundRecordSave = new ParamFundRecordSave();
        fundRecordSave.setShopId(param.getShopId());
        fundRecordSave.setUserId(param.getUserId());
        fundRecordSave.setMoney(param.getInitPrice());
        fundRecordSave.setState(EnumFinShopRecordInoutType.OUT.getName());
        fundRecordSave.setFundType(ConstantCommon.TEN);
        fundRecordSave.setCount(ConstantCommon.ONE);
        fundRecordSave.setFinClassifyName("入库记录");
        fundRecordSave.setAttributeCode(ConstantCommon.TEN);
        return fundRecordSave;
    }

    /**
     * 拼接店铺操作日志请求参数
     *
     * @param param
     * @return
     */
    public ParamShpOperateLogSave jointShpOperateLog(ParamProductSave param) {
        ParamShpOperateLogSave shpOperateLogSave = new ParamShpOperateLogSave();
        shpOperateLogSave.setShopId(param.getShopId());
        shpOperateLogSave.setOperateUserId(param.getUserId());
        shpOperateLogSave.setModuleName(EnumShpOperateLogModule.PROD.getName());
        shpOperateLogSave.setOperateName("商品入库");
        String beforeValue = getBeforeValue(param);
        String prodName = param.getName() + "商品信息：" + beforeValue;
        shpOperateLogSave.setOperateContent(prodName);
        shpOperateLogSave.setProdId(param.getProId());
        shpOperateLogSave.setRequest(param.getRequest());
        return shpOperateLogSave;
    }

    /**
     * 获取修改前信息
     *
     * @param param
     * @return
     */
    @Override
    public String getBeforeValue(ParamProductSave param) {
        return "成本价:" + formatPrice(param.getInitPrice()) + ",同行价:" + formatPrice(param.getTradePrice())
                + ",代理价:" + formatPrice(param.getAgencyPrice()) + ",销售价:" + formatPrice(param.getSalePrice()) + ",商品属性:"
                + servicesUtil.getAttributeCn(ConstantNums.TEN.toString(), true);
    }

    /**
     * 格式化价格
     *
     * @param object
     * @return
     */
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
    public void updateProduct(ParamProductUpdate param) {
        Date dateTime = new Date();
        Integer userId = param.getUserId();
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(param);
        //添加商品信息
        ProProduct proProduct = (ProProduct) map.get("proProduct");
        proProduct.setUpdateTime(dateTime);
        proProduct.setUpdateAdmin(userId);
        updateById(proProduct);
        //修改商品详情
        ProDetail proDetail = (ProDetail) map.get("proDetail");
        proDetail.setUpdateTime(dateTime);
        proDetail.setUpdateAdmin(userId);
        proDetail.setFkProProductId(proProduct.getId());
        proDetailService.updateProDetail(proDetail);
        productEsService.jointProInfo(proProduct, proDetail, ConstantCommon.ES_UPDATE);
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
    public Map<String, Object> checkProductParam(ParamProductUpdate param) {
        ProProduct proProduct = new ProProduct();
        ProDetail proDetail = new ProDetail();
        if (!LocalUtils.isEmptyAndNull(param.getBizId())) {
            //查询商品信息
            proProduct = getProductById(Integer.parseInt(param.getBizId()));
            //擦亮时间
            proProduct.setRefreshTime(new Date());
            if (proProduct.getFkProStateCode() >= EnumProState.SALE_40.getCode() || proProduct.getFkProStateCode() < EnumProState.STAND_BY_10.getCode()) {
                //todo 提示 【未上架】和【已上架】状态的商品才能修改
            }
            //查询商品详情
            proDetail = proDetailService.getProDetailByProId(proProduct.getId());
        }
        proProduct.setFkShpShopId(param.getShopId());
        proProduct.setInsertAdmin(param.getUserId());
        //验证当前店铺是否已经添加了30件商品信息
        if (isUpload(param.getShopId())) {
            //todo 这个地方抛出提示不能超过30件商品
            return null;
        }
        //详情图片
        if (!LocalUtils.isEmptyAndNull(param.getProductList()) && param.getProductList().size() <= ConstantNums.EIGHT) {
            //todo 提示详情图片数量不能超过8张
        }
        //将首图添加到详情图片中到第一张
        param.getProductList().add(0, param.getFirstImg());
        proDetail.setProductImg(LocalUtils.getStringFromList(param.getProductList()));
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
            proProduct.setFkProPublicId(proBrandModel.getFkProPublicId());
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
