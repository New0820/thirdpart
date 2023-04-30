package com.example.temp.entity.fin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 资金流水记录表 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:32:50
 */
@ApiModel(description = "资金流水记录表")
@Data
public class FinFundRecord {


    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    private Integer id;
    /**
     * 账单id
     */
    @ApiModelProperty(value = "账单id")
    private Integer fkFinBillId;
    /**
     * money
     */
    @ApiModelProperty(value = "money")
    private BigDecimal money;
    /**
     * init_price
     */
    @ApiModelProperty(value = "init_price")
    private BigDecimal initPrice;
    /**
     * 状态 0:收入; 1:支出
     */
    @ApiModelProperty(value = "状态 0:收入; 1:支出")
    private String state;
    /**
     * 流水发生途径 10:app内部交易; 20:外部导入(用户通过app自建账单);21:外部导入(通过web后台导入excel);30:其它途径
     */
    @ApiModelProperty(value = "流水发生途径 10:app内部交易; 20:外部导入(用户通过app自建账单);21:外部导入(通过web后台导入excel);30:其它途径")
    private String way;
    /**
     * 分类名称+shopId和fin_classify表对应;
     */
    @ApiModelProperty(value = "分类名称+shopId和fin_classify表对应;")
    private String finClassifyName;
    /**
     * 分类名称 10:商品销售; 20:维修服务;30薪资;40:其他收支;50:质押商品
     */
    @ApiModelProperty(value = "分类名称 10:商品销售; 20:维修服务;30薪资;40:其他收支;50:质押商品")
    private String fundType;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * ord_order的主键Id,逻辑id,软件内部关联;0代表没有该订单记录;
     */
    @ApiModelProperty(value = "ord_order的主键Id,逻辑id,软件内部关联;0代表没有该订单记录;")
    private Integer fkOrdOrderId;
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
     * 账单发生时间
     */
    @ApiModelProperty(value = "账单发生时间")
    private Date happenTime;
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
