package com.example.temp.param.pro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author new
 */
@Data
@ApiModel(description = "修改商品信息")
public class ParamProductUpdate extends ParamProductSave {

    @ApiModelProperty(name = "bidId", value = "bidId", required = true)
    private String bizId;


}
