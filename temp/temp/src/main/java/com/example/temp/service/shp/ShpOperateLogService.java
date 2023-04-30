package com.example.temp.service.shp;

import com.example.temp.param.shp.ParamShpOperateLogSave;

/**
 * 店铺操作日志表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:31:50
 */
public interface ShpOperateLogService {

    /**
     * 添加店铺操作日志
     *
     * @param param
     */
    void saveShpOperateLog(ParamShpOperateLogSave param);
}
