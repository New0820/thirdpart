package com.example.temp.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author new
 */
@Data
@ApiModel(description = "修改商品信息")
public class ParamUpdateProduct extends ParamSaveProduct{

    @ApiModelProperty(name = "proId", value = "商品id", required = true)
    private String proId;


}
