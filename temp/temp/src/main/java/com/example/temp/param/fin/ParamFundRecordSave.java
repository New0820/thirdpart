package com.example.temp.param.fin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 账单记录表
 *
 * @author taoqimin
 * @date 2023年4月30日20:09:25
 */
@Data
@ApiModel(value = "账单记录新增类", description = "账单记录新增类")
public class ParamFundRecordSave {

    /**
     * shp_shop的id字段,主键id
     */
    private Integer shopId;


    private Integer userId;

    /**
     * 交易金额(分)
     */
    private String money;
    /**
     * 成本价(分)
     */
    private String initPrice;
    /**
     * 状态 in:收入; out:支出
     */
    private String state;
    /**
     * 分类名称 10 入库商品；20:商品销售; 30:维修服务;40薪资;50:其他收支;60:质押商品
     */
    private String fundType;
    /**
     * 数量
     */
    private String count;
    /**
     * 分类名称+shopId和fin_classify表对应;
     */
    private String finClassifyName;
    /**
     * 商品属性;10:自有商品; 20:寄卖商品; 30:质押(典当)商品; 40:其他商品
     */
    private String attributeCode;


}
