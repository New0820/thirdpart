package com.example.temp.param.shp;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * 新增店铺操作日志--前端参数模型
 *
 * @author 时间
 * @Date 2023年4月30日18:26:11
 */
@Data
public class ParamShpOperateLogSave {

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 操作用户ID
     */
    private Integer operateUserId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 操作业务类型名称
     */
    private String operateName;

    /**
     * 操作内容
     */
    private String operateContent;

    /**
     * 操作后的内容
     */
    private String operateAfter;

    /**
     * 商品ID
     */
    private Integer prodId;

    /**
     * 请求request
     */
    private HttpServletRequest request;

    private String shopName;

}
