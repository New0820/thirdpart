package com.example.temp.common.base;

import com.example.temp.common.response.EnumResponseMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @authortaoqimin
 * Happy Coding, Happy Life
 * @Description: Result回调方法包装类
 * 约定所有回调信息都通过此方法构建
 * <p>
 * 成功回调:
 * - 默认成功信息返回
 * - 泛型对象成功信息返回
 * - 泛型对象及自定义说明成功信息返回
 * <p>
 * 失败回调:
 * - 默认失败信息返回
 * - 泛型对象失败信息返回
 * - 操作码对象失败信息返回
 * - 操作码对象及泛型对象失败信息返回
 * - 自定义说明失败信息返回
 * <p>
 * 回调类型判断
 * @date 2019-12-02 19:44:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回说明")
public class BaseResult<T> implements Serializable {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "返回编码")
    private String code;

    /**
     * 状态码说明
     */
    @ApiModelProperty(value = "描述信息")
    private String msg;

    /**
     * 回调数据对象
     */
    @ApiModelProperty(value = "业务数据")
    private T data;


    public static BaseResult okResult(EnumResponseMessage responseMessage) {
        BaseResult result = new BaseResult<>();
        result.setCode(responseMessage.getCode());
        result.setMsg(responseMessage.getMsg());
        result.setData(null);
        return result;
    }

    public static <D> BaseResult<D> okResult(EnumResponseMessage responseMessage, D data) {
        BaseResult<D> result = new BaseResult<>();
        result.setCode(responseMessage.getCode());
        result.setMsg(responseMessage.getMsg());
        result.setData(data);
        return result;
    }
}
