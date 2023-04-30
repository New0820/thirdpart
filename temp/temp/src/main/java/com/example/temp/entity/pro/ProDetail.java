package com.example.temp.entity.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 产品详情表;该表和(pro_product)一对一关系 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:28
 */
@ApiModel(description = "产品详情表;该表和(pro_product)一对一关系")
@Data
public class ProDetail {


    /**
     * 主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联")
    private Integer id;
    /**
     * pro_product的主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "pro_product的主键Id,逻辑id,软件内部关联")
    private Integer fkProProductId;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * 配件
     */
    @ApiModelProperty(value = "配件")
    private String accessory;
    /**
     * 商品来源
     */
    @ApiModelProperty(value = "商品来源")
    private String source;
    /**
     * 购买渠道
     */
    @ApiModelProperty(value = "购买渠道")
    private String buyChannel;
    /**
     * 流行款式
     */
    @ApiModelProperty(value = "流行款式")
    private String popStyle;
    /**
     * 材质
     */
    @ApiModelProperty(value = "材质")
    private String material;
    /**
     * 鞋码
     */
    @ApiModelProperty(value = "鞋码")
    private String shoesSize;
    /**
     * 委托方--姓名
     */
    @ApiModelProperty(value = "委托方--姓名")
    private String customerName;
    /**
     * 委托方--手机号码
     */
    @ApiModelProperty(value = "委托方--手机号码")
    private String customerPhone;
    /**
     * 委托方--备注
     */
    @ApiModelProperty(value = "委托方--备注")
    private String customerRemark;
    /**
     * 保卡;0:没有; 1:有
     */
    @ApiModelProperty(value = "保卡;0:没有; 1:有")
    private Character repairCard;
    /**
     * 保卡有效时间;有用户自定义
     */
    @ApiModelProperty(value = "保卡有效时间;有用户自定义")
    private String repairCardTime;
    /**
     * 产品独立(唯一)编码,用户自填;
     */
    @ApiModelProperty(value = "产品独立(唯一)编码,用户自填;")
    private String uniqueCode;
    /**
     * 保卡图片地址或者独立编码图片地址
     */
    @ApiModelProperty(value = "保卡图片地址或者独立编码图片地址")
    private String cardCodeImg;
    /**
     * 产品图片地址,多个用英文分号隔开
     */
    @ApiModelProperty(value = "产品图片地址,多个用英文分号隔开")
    private String productImg;
    /**
     * 瑕疵图
     */
    @ApiModelProperty(value = "瑕疵图")
    private String flawImg;
    /**
     * 视频地址
     */
    @ApiModelProperty(value = "视频地址")
    private String videoUrl;
    /**
     * 插入时间
     */
    @ApiModelProperty(value = "插入时间")
    private Date insertTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 修改用户_管理员id
     */
    @ApiModelProperty(value = "修改用户_管理员id")
    private Integer updateAdmin;
    /**
     * 版本号;用于更新时对比操作;
     */
    @ApiModelProperty(value = "版本号;用于更新时对比操作;")
    private Integer versions;
    /**
     * 是否逻辑删除;0:false:不删除;1:true:逻辑删除;对用户显示，所有查询sql都要带上del=0这个条件
     */
    @ApiModelProperty(value = "是否逻辑删除;0:false:不删除;1:true:逻辑删除;对用户显示，所有查询sql都要带上del=0这个条件")
    private Character del;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 商品备注图片地址
     */
    @ApiModelProperty(value = "商品备注图片地址")
    private String remarkImgUrl;
    /**
     * 商品委托方信息
     */
    @ApiModelProperty(value = "商品委托方信息")
    private String customerInfo;
    /**
     * 商品货号;商品分类前缀+商品id;eg:WD10050
     */
    @ApiModelProperty(value = "商品货号;商品分类前缀+商品id;eg:WD10050")
    private String autoNumber;
}
