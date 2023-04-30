package com.example.temp.entity.shp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 店铺--用户与店铺对应关系(多对多) bean
 *
 * @author taoqimin
 * @Date 2023-04-30 18:33:06
 */
@ApiModel(description = "店铺--用户与店铺对应关系(多对多)")
@Data
public class ShpUserShopRef {


    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * 店铺成员ID
     */
    @ApiModelProperty(value = "店铺成员ID")
    private Integer fkShpUserId;
    /**
     * 在该店铺里的昵称,对该店铺所有成员可见
     */
    @ApiModelProperty(value = "在该店铺里的昵称,对该店铺所有成员可见")
    private String name;
    /**
     * 用户类型表的id
     */
    @ApiModelProperty(value = "用户类型表的id")
    private Integer fkShpUserTypeId;
    /**
     * -9:经营者; -2:店长；-1：管理员；0：普通人员；1：代理；2: 访客
     */
    @ApiModelProperty(value = "-9:经营者; -2:店长；-1：管理员；0：普通人员；1：代理；2: 访客")
    private Integer type;
    /**
     * 状态   -9:已逻辑删除; 0：禁用(或辞职)   1：正常
     */
    @ApiModelProperty(value = "状态   -9:已逻辑删除; 0：禁用(或辞职)   1：正常")
    private String state;
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechat;
    /**
     * 微信二维码
     */
    @ApiModelProperty(value = "微信二维码")
    private String qrCode;
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

}
