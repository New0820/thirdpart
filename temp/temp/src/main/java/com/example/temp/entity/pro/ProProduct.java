package com.example.temp.entity.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 产品表;该表和(pro_detail)一对一关系 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:17
 */
@Data
@ApiModel(description = "产品表;该表和(pro_detail)一对一关系")
public class ProProduct {


    /**
     * 主键Id,逻辑id,软件内部关联;不要把此id暴露在前端;
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联;不要把此id暴露在前端;")
    private Integer id;
    /**
     * 产品唯一标识符,业务id,此id对外开放
     */
    @ApiModelProperty(value = "产品唯一标识符,业务id,此id对外开放")
    private String bizId;
    /**
     * 抖店平台分类id
     */
    @ApiModelProperty(value = "抖店平台分类id")
    private Integer doudianClassifyId;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * 产品状态表的code
     */
    @ApiModelProperty(value = "产品状态表的code")
    private Integer fkProStateCode;
    /**
     * 产品属性表的code
     */
    @ApiModelProperty(value = "产品属性表的code")
    private String fkProAttributeCode;
    /**
     * 产品分类表的code;默认':无分类;
     */
    @ApiModelProperty(value = "产品分类表的code;默认':无分类;")
    private String fkProClassifyCode;
    /**
     * 商品二级分类表pro_classify_sub的name字段
     */
    @ApiModelProperty(value = "商品二级分类表pro_classify_sub的name字段")
    private String fkProClassifySubId;
    /**
     * pro_recycle_type的id字段,回收类型主键
     */
    @ApiModelProperty(value = "pro_recycle_type的id字段,回收类型主键")
    private Integer fkProRecycleId;
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private String fkProTagId;
    /**
     * 商品二级分类表pro_classify_sub的name字段
     */
    @ApiModelProperty(value = "商品二级分类表pro_classify_sub的name字段")
    private String fkProClassifySubName;
    /**
     * 商品分类系列;默认':无分类;
     */
    @ApiModelProperty(value = "商品分类系列;默认':无分类;")
    private String fkProSubSeriesName;
    /**
     * 商品分类型号;默认':无分类;
     */
    @ApiModelProperty(value = "商品分类型号;默认':无分类;")
    private String fkProSeriesModelName;
    /**
     * 公价id
     */
    @ApiModelProperty(value = "公价id")
    private Integer fkProPublicId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 产品描述
     */
    @ApiModelProperty(value = "产品描述")
    private String description;
    /**
     * 货号
     */
    @ApiModelProperty(value = "货号")
    private String articleNumber;
    /**
     * 商品成色
     */
    @ApiModelProperty(value = "商品成色")
    private String quality;
    /**
     * 适用人群;男; 女; 通用;
     */
    @ApiModelProperty(value = "适用人群;男; 女; 通用;")
    private String targetUser;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;
    /**
     * 该商品总库存;
     */
    @ApiModelProperty(value = "该商品总库存;")
    private Integer totalNum;
    /**
     * 卖出库存
     */
    @ApiModelProperty(value = "卖出库存")
    private Integer saleNum;
    /**
     * 线上展会卖出数量
     */
    @ApiModelProperty(value = "线上展会卖出数量")
    private Integer unionNum;
    /**
     * 是否推荐产品；0：不推荐；1：推荐; 目前预留字段
     */
    @ApiModelProperty(value = "是否推荐产品；0：不推荐；1：推荐; 目前预留字段")
    private Character hot;
    /**
     * 是否分享产品；10：不分享；20：分享给友商; 21:分享给代理; 22:分享给所有人(任何一级分享都可以分享给用户看,除非不分享)
     */
    @ApiModelProperty(value = "是否分享产品；10：不分享；20：分享给友商; 21:分享给代理; 22:分享给所有人(任何一级分享都可以分享给用户看,除非不分享)")
    private String share;
    /**
     * 闲鱼同步状态 0:不允许同步 1:允许同步
     */
    @ApiModelProperty(value = "闲鱼同步状态 0:不允许同步 1:允许同步")
    private String xianyuSyncState;
    /**
     * 验货宝 0:不开启 1:开启
     */
    @ApiModelProperty(value = "验货宝 0:不开启 1:开启")
    private String yanhuobao;
    /**
     * 快手同步状态0:不允许同步 1:允许同步
     */
    @ApiModelProperty(value = "快手同步状态0:不允许同步 1:允许同步")
    private String kuaishouSyncState;
    /**
     * 抖店同步状态0:不允许同步 1:允许同步
     */
    @ApiModelProperty(value = "抖店同步状态0:不允许同步 1:允许同步")
    private String doudianSyncState;
    /**
     * 95分同步状态0:不允许同步 1:允许同步
     */
    @ApiModelProperty(value = "95分同步状态0:不允许同步 1:允许同步")
    private String ninetyfiveSyncState;
    /**
     * 锁单来源：ninetyfive:95分
     */
    @ApiModelProperty(value = "锁单来源：ninetyfive:95分")
    private String lockSource;
    /**
     * init_price
     */
    @ApiModelProperty(value = "init_price")
    private BigDecimal initPrice;
    /**
     * 成本价格
     */
    @ApiModelProperty(value = "成本价格")
    private BigDecimal tradePrice;
    /**
     * 代理价格
     */
    @ApiModelProperty(value = "代理价格")
    private BigDecimal agencyPrice;
    /**
     * 销售价格
     */
    @ApiModelProperty(value = "销售价格")
    private BigDecimal salePrice;
    /**
     * 国内公价(元)
     */
    @ApiModelProperty(value = "国内公价(元)")
    private Integer publicPrice;
    /**
     * finish_price
     */
    @ApiModelProperty(value = "finish_price")
    private BigDecimal finishPrice;
    /**
     * 缩略图地址
     */
    @ApiModelProperty(value = "缩略图地址")
    private String smallImg;
    /**
     * 公价商品图片
     */
    @ApiModelProperty(value = "公价商品图片")
    private String publicProductImg;
    /**
     * 插入时间的日期格式
     */
    @ApiModelProperty(value = "插入时间的日期格式")
    private Date bizDate;
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
     * 产品上架时间
     */
    @ApiModelProperty(value = "产品上架时间")
    private Date releaseTime;
    /**
     * 锁定时间;(时间结束之前,该产品不能被其他人卖掉)
     */
    @ApiModelProperty(value = "锁定时间;(时间结束之前,该产品不能被其他人卖掉)")
    private Date lockTime;
    /**
     * 锁单用户id
     */
    @ApiModelProperty(value = "锁单用户id")
    private Integer lockUserId;
    /**
     * 产品卖出时间
     */
    @ApiModelProperty(value = "产品卖出时间")
    private Date finishTime;
    /**
     * 产品保管结束时间
     */
    @ApiModelProperty(value = "产品保管结束时间")
    private Date saveEndTime;
    /**
     * 添加用户_管理员id;-9:微商相册导入; -8:段小狸导入;-7:excel导入
     */
    @ApiModelProperty(value = "添加用户_管理员id;-9:微商相册导入; -8:段小狸导入;-7:excel导入")
    private Integer insertAdmin;
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
     * 此状态不影响用户前端展示;商家联盟状态:0:不显示 | 1:显示
     */
    @ApiModelProperty(value = "此状态不影响用户前端展示;商家联盟状态:0:不显示 | 1:显示")
    private String unionState;
    /**
     * 删除备注
     */
    @ApiModelProperty(value = "删除备注")
    private String deleteRemark;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 回收人员
     */
    @ApiModelProperty(value = "回收人员")
    private Integer recycleAdmin;
    /**
     * 回收图片
     */
    @ApiModelProperty(value = "回收图片")
    private String recycleImg;
    /**
     * 鉴定人员(用户表id)
     */
    @ApiModelProperty(value = "鉴定人员(用户表id)")
    private Integer appraisalAdmin;
    /**
     * 取回备注
     */
    @ApiModelProperty(value = "取回备注")
    private String retrieveRemark;
    /**
     * 取回用户
     */
    @ApiModelProperty(value = "取回用户")
    private Integer fkShpRetrieveUserId;
    /**
     * 取回时间
     */
    @ApiModelProperty(value = "取回时间")
    private Date retrieveTime;
    /**
     * 寄卖传送类型 convey寄卖传送 warehouse仓库商品
     */
    @ApiModelProperty(value = "寄卖传送类型 convey寄卖传送 warehouse仓库商品")
    private String conveyState;
    /**
     * 寄卖传送id
     */
    @ApiModelProperty(value = "寄卖传送id")
    private Integer fkProConveyId;
    /**
     * 商家联盟商品：擦亮时间
     */
    @ApiModelProperty(value = "商家联盟商品：擦亮时间")
    private Date refreshTime;
    /**
     * 回收时间
     */
    @ApiModelProperty(value = "回收时间")
    private Date recycleTime;
    /**
     * 总附加成本
     */
    @ApiModelProperty(value = "总附加成本")
    private BigDecimal totalAdditionalCostPrice;

}
