package com.example.temp.entity.shp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 店铺管理员 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:55:41
 */
@ApiModel(description = "店铺管理员")
@Data
public class ShpUser {


    /**
     * 主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码;
     */
    @ApiModelProperty(value = "密码; ")
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 手机号;对称加密存储
     */
    @ApiModelProperty(value = "手机号;对称加密存储")
    private String phone;
    /**
     * -2:超级管理员(店长)；-1：管理员；0：普通人员；1：代理；2：访客；
     */
    @ApiModelProperty(value = "-2:超级管理员(店长)；-1：管理员；0：普通人员；1：代理；2：访客；")
    private String type;
    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty(value = "状态  0：禁用   1：正常")
    private String state;
    /**
     * 店铺用户编号(邀请码): 唯一索引
     */
    @ApiModelProperty(value = "店铺用户编号(邀请码): 唯一索引")
    private Integer number;
    /**
     * 默认登录上一次店铺;0:不默认登录; 1:默认登录该店铺
     */
    @ApiModelProperty(value = "默认登录上一次店铺;0:不默认登录; 1:默认登录该店铺")
    private Character defaultLogin;
    /**
     * 用户头像图片地址
     */
    @ApiModelProperty(value = "用户头像图片地址")
    private String headImgUrl;
    /**
     * shp_shop的id字段,主键id;默认直接登录店铺;0为没有关联店铺
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id;默认直接登录店铺;0为没有关联店铺")
    private Integer fkShpShopId;
    /**
     * 运营模块--渠道的主键id
     */
    @ApiModelProperty(value = "运营模块--渠道的主键id")
    private Integer fkOpChannelId;
    /**
     * 0:未绑定 | 1:已绑定
     */
    @ApiModelProperty(value = "0:未绑定 | 1:已绑定")
    private Integer bindWeChat;
    /**
     * 0:未绑定 | 1:已绑定
     */
    @ApiModelProperty(value = "0:未绑定 | 1:已绑定")
    private Integer bindApple;
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
     * 极光推送Registration ID
     */
    @ApiModelProperty(value = "极光推送Registration ID")
    private String jgPushRegId;
    /**
     * app版本
     */
    @ApiModelProperty(value = "app版本")
    private String appVersion;
    /**
     * 手机尾号
     */
    @ApiModelProperty(value = "手机尾号")
    private String phoneTail;

}
