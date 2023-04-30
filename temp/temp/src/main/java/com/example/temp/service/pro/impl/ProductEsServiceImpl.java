package com.example.temp.service.pro.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.entity.pro.ProDetail;
import com.example.temp.entity.pro.ProProduct;
import com.example.temp.entity.shp.ShpShop;
import com.example.temp.param.ParamProductEs;
import com.example.temp.service.pro.ProductEsService;
import com.example.temp.util.DateUtil;
import com.example.temp.util.EsUtil;
import com.example.temp.util.LocalUtils;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author cm
 */
@Slf4j
@Service
public class ProductEsServiceImpl implements ProductEsService {

    @Resource
    private RestHighLevelClient client;

    public static String idxNameV2() {
        String idxName;
        //开发
        if (ConstantCommon.DEV.equals(ConstantCommon.springProfilesActive)) {
            idxName = EsUtil.PRODUCT_IDX_DEV_V2 + "_news";
            //测试
        } else if (ConstantCommon.TEST.equals(ConstantCommon.springProfilesActive)) {
            idxName = EsUtil.PRODUCT_IDX_TEST_V2 + "_news";
        } else {
            //生产
            idxName = EsUtil.PRODUCT_IDX_PRO_PUB_V2 + "_news";
        }
        return idxName;
    }


    /**
     * es 封装es中更新的字段信息
     *
     * @param pro       商品信息
     * @param proDetail 详情信息
     */
    @Override
    public void jointProInfo(ProProduct pro, ProDetail proDetail, String source) {
        ParamProductEs paramProductEs = new ParamProductEs();
        //系统编码
        paramProductEs.setAutoNumber(proDetail.getAutoNumber());
        paramProductEs.setProDel(0);
        //商品id
        paramProductEs.setProId(pro.getId());
        paramProductEs.setBizId(pro.getBizId());
        //店铺id
        paramProductEs.setShopId(pro.getFkShpShopId());
        paramProductEs.setProClassifyCode(pro.getFkProClassifyCode());
        //商品名称
        paramProductEs.setProName(pro.getName());
        //描述
        paramProductEs.setDescription(pro.getDescription());
        //商品备注
        paramProductEs.setRemark(pro.getRemark());
        //独立编码
        paramProductEs.setUniqueCode(proDetail.getUniqueCode());
        paramProductEs.setPublicId(pro.getFkProPublicId());
        //商品图片
        paramProductEs.setSmallImg(pro.getSmallImg());
        paramProductEs.setShopId(pro.getFkShpShopId());
        //刷新时间
        String refreshTime = DateUtil.format(pro.getRefreshTime(), DateUtil.YYYY_MM_DD_HH_MM_SS);
        paramProductEs.setShowTime(refreshTime);
        paramProductEs.setProSalePrice(pro.getSalePrice().toString());
        paramProductEs.setProTradePrice(pro.getTradePrice().longValue());
        paramProductEs.setProInitPrice(pro.getInitPrice().longValue());
        paramProductEs.setPublicId(pro.getFkProPublicId());
        //品牌名称
        paramProductEs.setProClassifySubName(LocalUtils.returnEmptyStringOrString(pro.getFkProClassifySubName()));
        //系列名称
        paramProductEs.setProSubSeriesName(LocalUtils.returnEmptyStringOrString(pro.getFkProSubSeriesName()));
        //型号名称
        paramProductEs.setProSeriesModelName(LocalUtils.returnEmptyStringOrString(pro.getFkProSeriesModelName()));
        paramProductEs.setProAttributeCode(Integer.parseInt(pro.getFkProAttributeCode()));
        paramProductEs.setProStateCode(pro.getFkProStateCode());
        //刷新时间
        paramProductEs.setProRefreshTime(refreshTime);
        if (ConstantCommon.ES_SAVE.equals(source)) {
            saveEsProProduct(paramProductEs);
        }
        if (ConstantCommon.ES_UPDATE.equals(source)) {
            updateEsProProduct(paramProductEs);
        }
    }


    /**
     * 调用es更新商品信息
     *
     * @param param
     */
    public void updateEsProProduct(ParamProductEs param) {
        boolean existsIndex = isExistsIndex(idxNameV2());
        if (!existsIndex) {
            return;
        }
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(idxNameV2());
        updateRequest.id(param.getProId().toString());
        updateRequest.type("_doc");
        Map<String, Object> kvs = LocalUtils.toMapByReflect(param);
        updateRequest.doc(kvs);
        //超时时间
        updateRequest.timeout(TimeValue.timeValueSeconds(1));
        updateRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        try {
            log.info("修改es商品信息,索引{},修改内容{}", idxNameV2(), JSONObject.toJSONString(param));
            client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("修改es商品信息异常,索引{},修改内容{},异常信息:{}", idxNameV2(), JSONObject.toJSONString(param), e.getMessage());
        }
    }


    /**
     * es添加商品信息
     * @param productEsParam
     */
    public void saveEsProProduct(ParamProductEs productEsParam) {
        BulkRequest bulkRequest = new BulkRequest();
        //构造保存请求
        IndexRequest indexRequest = new IndexRequest(idxNameV2());
        indexRequest.id(productEsParam.getProId().toString());
        indexRequest.type("_doc");
        String jsonString = JSON.toJSONString(productEsParam);
        indexRequest.source(jsonString, XContentType.JSON);
        bulkRequest.add(indexRequest);
        try {
            //调用bulk接口
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("=======商品批量save失败：{}=====", e.getMessage());
        }
    }


    /**
     * 检查索引是否存在
     *
     * @param idxName
     * @return
     */
    public boolean isExistsIndex(String idxName) {
        boolean exists = false;
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(idxName);
            getIndexRequest.humanReadable(true);
            exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("isExistsIndex方法异常");
        }
        return exists;
    }
}

