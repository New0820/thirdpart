package com.example.temp.param.pro;

import com.example.temp.common.annotate.ForUpdate;
import com.example.temp.common.annotate.ProUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author new
 */
@Data
@ApiModel(description = "商品详情")
public class ParamProductDetail{

    @ApiModelProperty(name = "proId", value = "商品id")
    private String proId;

    @ApiModelProperty(name = "productImg", value = "商品详情图")
    @ProUpdate(fieldName = "商品图片")
    private String productImg;

    @ApiModelProperty(name = "video", value = "视频")
    @ProUpdate(fieldName = "商品视频")
    private String video;

    @ApiModelProperty(name = "name", value = "商品名称")
    @ProUpdate(fieldName = "名称")
    private String name;

    @ApiModelProperty(name = "description", value = "商品描述")
    @ProUpdate(fieldName = "描述")
    private String description;

    @ApiModelProperty(name = "classify", value = "分类")
    @ProUpdate(fieldName = "分类")
    private String classify;

    @ApiModelProperty(name = "remark", value = "商品备注")
    @ProUpdate(fieldName = "商品备注")
    private String remark;

    @ApiModelProperty(name = "brandName", value = "品牌名称")
    @ProUpdate(fieldName = "品牌")
    private String brandName;

    @ApiModelProperty(name = "seriesName", value = "系列名称")
    @ProUpdate(fieldName = "系列")
    private String seriesName;

    @ApiModelProperty(name = "modelName", value = "型号名称")
    @ProUpdate(fieldName = "型号")
    private String modelName;

    @ForUpdate(fieldName = "成本价")
    @ApiModelProperty(value = "成本价格")
    private BigDecimal initPrice;

    @ForUpdate(fieldName = "同行价格")
    @ApiModelProperty(value = "同行价格")
    private BigDecimal tradePrice;

    @ApiModelProperty(value = "代理价格")
    @ForUpdate(fieldName = "代理价格")
    private BigDecimal agencyPrice;

    @ApiModelProperty(value = "销售价格")
    @ForUpdate(fieldName = "销售价格")
    private BigDecimal salePrice;
}
