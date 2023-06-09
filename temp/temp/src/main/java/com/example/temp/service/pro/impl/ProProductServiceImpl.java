package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.constant.ConstantRedisKey;
import com.example.temp.entity.pro.*;
import com.example.temp.enums.fin.EnumFinFundRecordClassifyName;
import com.example.temp.enums.fin.EnumFinShopRecordInoutType;
import com.example.temp.enums.pro.EnumProAttribute;
import com.example.temp.enums.pro.EnumProPriceName;
import com.example.temp.enums.pro.EnumProState;
import com.example.temp.enums.shp.EnumShpOperateLogModule;
import com.example.temp.enums.shp.EnumShpOperateLogTypeName;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.param.fin.ParamFundRecordSave;
import com.example.temp.param.pro.ParamProductDetail;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(ParamProductSave param) {
        ParamProductUpdate paramUpdateProduct = new ParamProductUpdate();
        BeanUtils.copyProperties(paramUpdateProduct, param);
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(paramUpdateProduct);
        ProProduct proProduct = (ProProduct) map.get("proProduct");
        Date date = new Date();
        //生成独立编码
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
        ParamShpOperateLogSave shpOperateLogSave = jointShpOperateLog(param,ConstantCommon.ES_SAVE,null);
        shpOperateLogService.saveShpOperateLog(shpOperateLogSave);
        //添加账单记录
        ParamFundRecordSave paramFundRecordSave = jointFundRecordSave(param);
        finBillService.saveProductFundRecord(paramFundRecordSave);
        //商品记录信息
        proModifyRecordService.saveProModifyRecord(param);
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
        fundRecordSave.setFinClassifyName(EnumFinFundRecordClassifyName.UPLOAD_RECORD.getMsg());
        fundRecordSave.setAttributeCode(ConstantCommon.TEN);
        return fundRecordSave;
    }

    /**
     * 拼接店铺操作日志请求参数
     *
     * @param param
     * @return
     */
    public ParamShpOperateLogSave jointShpOperateLog(ParamProductSave param,String source,String operateContent) {
        ParamShpOperateLogSave shpOperateLogSave = new ParamShpOperateLogSave();
        shpOperateLogSave.setShopId(param.getShopId());
        shpOperateLogSave.setOperateUserId(param.getUserId());
        shpOperateLogSave.setModuleName(EnumShpOperateLogModule.PROD.getName());
        if (ConstantCommon.ES_SAVE.equals(source)) {
            shpOperateLogSave.setOperateName(EnumShpOperateLogTypeName.PRO_UPLOAD.getMsg());
            String beforeValue = getBeforeValue(param);
            String prodName = param.getName() + "商品信息：" + beforeValue;
            shpOperateLogSave.setOperateContent(prodName);
        }
        if (ConstantCommon.ES_UPDATE.equals(source)){
            shpOperateLogSave.setOperateName(EnumShpOperateLogTypeName.PRO_UPDATE.getMsg());
            shpOperateLogSave.setOperateContent(operateContent);
        }
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
        return EnumProPriceName.INIT_PRICE + ":" + formatPrice(param.getInitPrice()) + "," +
                EnumProPriceName.TRADE_PRICE + ":" + formatPrice(param.getTradePrice()) + "," +
                EnumProPriceName.AGENCY_PRICE + ":" + formatPrice(param.getAgencyPrice()) + "," +
                EnumProPriceName.SALE_PRICE + ":" + formatPrice(param.getSalePrice()) + ",商品属性:"
                + servicesUtil.getAttributeCn(EnumProAttribute.OWN.getCode().toString(), true);
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
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(ParamProductUpdate param) {
        Date dateTime = new Date();
        Integer userId = param.getUserId();
        //对基础参数封装和校验
        Map<String, Object> map = checkProductParam(param);
        //添加商品信息
        ProProduct proProduct = (ProProduct) map.get("proProduct");
        //修改前的商品信息
        ProProduct oldProProduct = (ProProduct) map.get("oldProProduct");
        proProduct.setUpdateTime(dateTime);
        proProduct.setUpdateAdmin(userId);
        updateById(proProduct);
        //修改商品详情
        ProDetail proDetail = (ProDetail) map.get("proDetail");
        proDetail.setUpdateTime(dateTime);
        proDetail.setUpdateAdmin(userId);
        proDetail.setFkProProductId(proProduct.getId());
        proDetailService.updateProDetail(proDetail);
        //添加店铺日志信息 修改前，修改后
        String changedFields = LocalUtils.getChangedFields(oldProProduct, proProduct);
        ParamShpOperateLogSave shpOperateLogSave = jointShpOperateLog(param,ConstantCommon.ES_UPDATE,changedFields);
        shpOperateLogService.saveShpOperateLog(shpOperateLogSave);
        //修改前，修改后商品信息


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
        ProProduct oldProduct = new ProProduct();
        ProProduct newProduct = new ProProduct();
        ProDetail proDetail = new ProDetail();
        if (!LocalUtils.isEmptyAndNull(param.getBizId())) {
            //查询商品信息
            oldProduct = getProductById(Integer.parseInt(param.getBizId()));
            //擦亮时间
            newProduct.setRefreshTime(new Date());
            if (oldProduct.getFkProStateCode() >= EnumProState.SALE_40.getCode() || oldProduct.getFkProStateCode() < EnumProState.STAND_BY_10.getCode()) {
                //todo 提示 【未上架】和【已上架】状态的商品才能修改
            }
            //查询商品详情
            proDetail = proDetailService.getProDetailByProId(oldProduct.getId());

        }
        newProduct.setFkShpShopId(param.getShopId());
        newProduct.setInsertAdmin(param.getUserId());
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
            newProduct.setFkProClassifySubName(proBrand.getEnName());
        }
        //如果品牌id为空，系列id不为空
        if (LocalUtils.isEmptyAndNull(param.getBrandId()) && !LocalUtils.isEmptyAndNull(param.getSeriesId())) {
            //todo 提示请选择品牌
        } else {
            //获取系列名称
            ProBrandSeries proBrandSeries = proBrandSeriesService.getProBrandSeriesByIdOrBrandId(Integer.parseInt(param.getSeriesId()), Integer.parseInt(param.getBrandId()));
            newProduct.setFkProSubSeriesName(proBrandSeries.getName());
        }
        //如果品牌和系列为空，型号不为空
        boolean flag = LocalUtils.isEmptyAndNull(param.getBrandId()) || LocalUtils.isEmptyAndNull(param.getSeriesId());
        if (flag && !LocalUtils.isEmptyAndNull(param.getModelId())) {
            //todo 提示请选择系列
        } else {
            ProBrandModel proBrandModel = proBrandModelService.getByBrandModelByIdOrSeriesId(Integer.parseInt(param.getSeriesId()), Integer.parseInt(param.getModelId()));
            newProduct.setFkProSeriesModelName(proBrandModel.getName());
            newProduct.setFkProPublicId(proBrandModel.getFkProPublicId());
        }
        //商品名称
        newProduct.setName(param.getName());
        //商品属性
        newProduct.setFkProAttributeCode(ConstantNums.TEN.toString());
        //商品分类
        newProduct.setFkProClassifyCode(param.getClassify());
        //商品首图
        newProduct.setSmallImg(param.getFirstImg());
        //商品描述
        newProduct.setDescription(LocalUtils.returnEmptyStringOrString(param.getDescription()));
        //商品备注
        newProduct.setRemark(LocalUtils.returnEmptyStringOrString(param.getRemark()));
        //成本价格
        newProduct.setInitPrice(new BigDecimal(param.getInitPrice()));
        //销售价格
        newProduct.setSalePrice(new BigDecimal(param.getSalePrice()));
        //同行价格
        newProduct.setTradePrice(new BigDecimal(param.getTradePrice()));
        //代理价格
        newProduct.setAgencyPrice(new BigDecimal(param.getAgencyPrice()));
        //商品库存默认为1
        newProduct.setTotalNum(ConstantNums.ONE);
        //独立编码
        proDetail.setUniqueCode(param.getUniqueCode());
        //视频图片
        proDetail.setVideoUrl(LocalUtils.returnEmptyStringOrString(param.getVideo()));



        Map<String, Object> map = new HashMap<>(10);
        map.put("proProduct", newProduct);
        map.put("proDetail", proDetail);
        map.put("oldProduct", oldProduct);
        return map;
    }

    public static void main(String[] args) {
        ProProduct oldProduct = new ProProduct();
        oldProduct.setInitPrice(new BigDecimal("20"));
        oldProduct.setTradePrice(new BigDecimal("30"));
        oldProduct.setAgencyPrice(new BigDecimal("40"));
        oldProduct.setSalePrice(new BigDecimal("50"));
        oldProduct.setName("李大炮");
        oldProduct.setDescription("测试1");
        ProProduct newProduct = new ProProduct();
        newProduct.setInitPrice(new BigDecimal("220"));
        newProduct.setTradePrice(new BigDecimal("330"));
        newProduct.setAgencyPrice(new BigDecimal("440"));
        newProduct.setSalePrice(new BigDecimal("540"));
        newProduct.setName("李大炮1");
        newProduct.setDescription("测试2");
        System.out.println(LocalUtils.getChangedFields(oldProduct, newProduct));
    }
}
