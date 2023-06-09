package com.example.temp.param.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@ApiModel(description = "添加商品信息")
public class ParamProductSave {

    @ApiModelProperty(name = "name", value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(name = "description", value = "商品描述", required = false)
    private String description;

    @ApiModelProperty(name = "remark", value = "商品备注", required = false)
    private String remark;

    @ApiModelProperty(name = "firstImg", value = "商品首图", required = true)
    private String firstImg;

    @ApiModelProperty(name = "productList", value = "商品详情图", required = false)
    private List<String> productList;

    @ApiModelProperty(name = "video", value = "视频", required = false)
    private String video;

    @ApiModelProperty(name = "classify", value = "分类", required = true)
    private String classify;

    @ApiModelProperty(name = "brandId", value = "品牌", required = false)
    private String brandId;

    @ApiModelProperty(name = "seriesId", value = "系列id", required = false)
    private String seriesId;

    @ApiModelProperty(name = "modelId", value = "型号id", required = false)
    private String modelId;

    @ApiModelProperty(name = "initPrice", value = "成本价", required = true)
    private String initPrice;

    @ApiModelProperty(name = "tradePrice", value = "同行价", required = true)
    private String tradePrice;

    @ApiModelProperty(name = "agencyPrice", value = "代理价", required = true)
    private String agencyPrice;

    @ApiModelProperty(name = "salePrice", value = "销售价", required = true)
    private String salePrice;

    @ApiModelProperty(name = "uniqueCode", value = "独立编码", required = false)
    private String uniqueCode;

    @ApiModelProperty(hidden = true)
    private HttpServletRequest request;

    @ApiModelProperty(hidden = true)
    private Integer userId;

    @ApiModelProperty(hidden = true)
    private Integer shopId;

    @ApiModelProperty(hidden = true)
    private Integer proId;

    @ApiModelProperty(hidden = true)
    private String shopName;
}
