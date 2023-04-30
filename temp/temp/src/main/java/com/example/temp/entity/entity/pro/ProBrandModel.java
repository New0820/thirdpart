package com.example.temp.entity.entity.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 商品型号表 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:42
 */
@ApiModel(description = "商品型号表")
@Data
public class ProBrandModel {


    /**
     * 主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联")
    private Integer id;
    /**
     * 型号名称
     */
    @ApiModelProperty(value = "型号名称")
    private String name;
    /**
     * 俗称
     */
    @ApiModelProperty(value = "俗称")
    private String vulgarName;
    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String fkProClassifyCode;
    /**
     * 系列id
     */
    @ApiModelProperty(value = "系列id")
    private Integer fkProBrandSeriesId;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * 公价id
     */
    @ApiModelProperty(value = "公价id")
    private Integer fkProPublicId;
    /**
     * 类型;0:系统自带;1:用户自建
     */
    @ApiModelProperty(value = "类型;0:系统自带;1:用户自建")
    private String type;
    /**
     * 状态;-1:已删除;0:未使用;1:使用中
     */
    @ApiModelProperty(value = "状态;-1:已删除;0:未使用;1:使用中")
    private Integer state;
    /**
     * 序号排序
     */
    @ApiModelProperty(value = "序号排序")
    private Integer sort;
    /**
     * 插入时间
     */
    @ApiModelProperty(value = "插入时间")
    private Date insertTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 是否逻辑删除;0:false:不删除;1:true:逻辑删除;对用户显示，所有查询sql都要带上del=0这个条件
     */
    @ApiModelProperty(value = "是否逻辑删除;0:false:不删除;1:true:逻辑删除;对用户显示，所有查询sql都要带上del=0这个条件")
    private String del;

}
