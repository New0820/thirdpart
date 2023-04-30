package com.example.temp.entity.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 商品修改记录表 bean
 *
 * @author taoqimin
 * @Date 2023-04-30 17:34:56
 */
@ApiModel(description = "商品修改记录表")
@Data
public class ProModifyRecord {


    /**
     * 主键Id,逻辑id,软件内部关联
     */
    @ApiModelProperty(value = "主键Id,逻辑id,软件内部关联")
    private Integer id;
    /**
     * shp_shop的id字段,主键id
     */
    @ApiModelProperty(value = "shp_shop的id字段,主键id")
    private Integer fkShpShopId;
    /**
     * shp_user的id字段,主键id
     */
    @ApiModelProperty(value = "shp_user的id字段,主键id")
    private Integer fkShpUserId;
    /**
     * pro_product的id字段,主键id
     */
    @ApiModelProperty(value = "pro_product的id字段,主键id")
    private Integer fkProProductId;
    /**
     * 类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除
     */
    @ApiModelProperty(value = "类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除")
    private String type;
    /**
     * 属性名称;
     */
    @ApiModelProperty(value = "属性名称;")
    private String attributeName;
    /**
     * 类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除
     */
    @ApiModelProperty(value = "类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除")
    private String beforeValue;
    /**
     * 类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除
     */
    @ApiModelProperty(value = "类型;入库,上架,锁单,下架,修改,开单,退货,下载图片,分享,删除")
    private String afterValue;
    /**
     * 插入时间
     */
    @ApiModelProperty(value = "插入时间")
    private Date insertTime;
    /**
     * 来源 ios；android；pc
     */
    @ApiModelProperty(value = "来源 ios；android；pc")
    private String source;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
