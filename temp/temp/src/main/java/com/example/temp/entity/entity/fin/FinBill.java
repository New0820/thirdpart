package com.example.temp.entity.entity.fin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 帐单表 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:33:37
 */
@ApiModel(description = "帐单表")
@Data
public class FinBill {


    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /**
     * shopId
     */
    @ApiModelProperty(value = "shopId")
    private Integer fkShpShopId;
    /**
     * 账单名称
     */
    @ApiModelProperty(value = "账单名称")
    private String name;
    /**
     * 账单金额
     */
    @ApiModelProperty(value = "账单金额")
    private BigDecimal money;
    /**
     * 初始账单金额
     */
    @ApiModelProperty(value = "初始账单金额")
    private BigDecimal oldMoney;
    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private BigDecimal totalMoney;
    /**
     * 对账类型逗号分隔
     */
    @ApiModelProperty(value = "对账类型逗号分隔")
    private String types;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date insertTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer insertAdmin;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateAdmin;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 对账状态 10：进行中 -99 已删除
     */
    @ApiModelProperty(value = "对账状态 10：进行中 -99 已删除")
    private String state;

}
