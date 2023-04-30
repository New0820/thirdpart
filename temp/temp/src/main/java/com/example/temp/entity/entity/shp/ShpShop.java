package com.example.temp.entity.entity.shp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 店铺表; bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:55:23
 */
@ApiModel(description = "店铺表;")
@Data
public class ShpShop {


    /**
     * 主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联")
    private Integer id;
    /**
     * 店铺编号;
     */
    @ApiModelProperty(value = "店铺编号;")
    private String number;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String name;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 区/县
     */
    @ApiModelProperty(value = "区/县")
    private String district;
    /**
     * 行政区划代码; 如果district有值,则,取district的行政区划代码, 否则取城市的行政区划代码
     */
    @ApiModelProperty(value = "行政区划代码; 如果district有值,则,取district的行政区划代码, 否则取城市的行政区划代码")
    private String adCode;
    /**
     * 店铺地址
     */
    @ApiModelProperty(value = "店铺地址")
    private String address;
    /**
     * 店铺描述
     */
    @ApiModelProperty(value = "店铺描述")
    private String desc;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contact;
    /**
     * 封面图片地址
     */
    @ApiModelProperty(value = "封面图片地址")
    private String coverImgUrl;
    /**
     * 店铺头像地址
     */
    @ApiModelProperty(value = "店铺头像地址")
    private String headImgUrl;
    /**
     * 店铺管理员id.店长id
     */
    @ApiModelProperty(value = "店铺管理员id.店长id")
    private Integer fkShpUserId;
    /**
     * 店铺状态的code;详情查看shp_state表
     */
    @ApiModelProperty(value = "店铺状态的code;详情查看shp_state表")
    private Integer fkShpStateCode;
    /**
     * 店铺属性的code;详情查看shp_attribute表
     */
    @ApiModelProperty(value = "店铺属性的code;详情查看shp_attribute表")
    private Integer fkShpAttributeCode;
    /**
     * 认证状态;0:未认证; 1:已认证
     */
    @ApiModelProperty(value = "认证状态;0:未认证; 1:已认证")
    private Character validateState;
    /**
     * 付费月数: 0:未付费; 1:月付; 3:季付; 6:半年付; 12:年付; 36:三年付;
     */
    @ApiModelProperty(value = "付费月数: 0:未付费; 1:月付; 3:季付; 6:半年付; 12:年付; 36:三年付;")
    private Integer payMonth;
    /**
     * 试用开始时间
     */
    @ApiModelProperty(value = "试用开始时间")
    private Date tryStartTime;
    /**
     * 试用结束时间
     */
    @ApiModelProperty(value = "试用结束时间")
    private Date tryEndTime;
    /**
     * 付费使用开始时间
     */
    @ApiModelProperty(value = "付费使用开始时间")
    private Date payStartTime;
    /**
     * 付费使用结束时间
     */
    @ApiModelProperty(value = "付费使用结束时间")
    private Date payEndTime;
    /**
     * 会员加活动结束时间
     */
    @ApiModelProperty(value = "会员加活动结束时间")
    private Date payEndTimeOld;
    /**
     * 管理员续时时长;(一般用于帐号到期,可以延长到期时间;最长一个星期)
     */
    @ApiModelProperty(value = "管理员续时时长;(一般用于帐号到期,可以延长到期时间;最长一个星期)")
    private Date adminAddTime;
    /**
     * 活动添加的总时长
     */
    @ApiModelProperty(value = "活动添加的总时长")
    private BigDecimal totalHours;
    /**
     * 累计付费月数
     */
    @ApiModelProperty(value = "累计付费月数")
    private Integer totalMonth;
    /**
     * 是否是会员 yes|是会员 no|不是会员
     */
    @ApiModelProperty(value = "是否是会员 yes|是会员 no|不是会员")
    private String isMember;
    /**
     * 0:非会员; 1:体验会员;2:正式会员;3:靓号会员
     */
    @ApiModelProperty(value = "0:非会员; 1:体验会员;2:正式会员;3:靓号会员")
    private Integer memberState;
    /**
     * 小程序封面图片地址
     */
    @ApiModelProperty(value = "小程序封面图片地址")
    private String miniProgramCoverImgUrl;
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
     * 添加用户_管理员id
     */
    @ApiModelProperty(value = "添加用户_管理员id")
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 关联工作微信id
     */
    @ApiModelProperty(value = "关联工作微信id")
    private Integer fkSysJobWxId;
}
