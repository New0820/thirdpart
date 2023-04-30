package com.example.temp.service.pro;

import com.example.temp.param.pro.ParamProductSave;

/**
 * 商品修改记录表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:34:56
 */
public interface ProModifyRecordService {


    /**
     * 添加商品操作记录信息
     *
     * @param param
     */
    void saveProModifyRecord(ParamProductSave param);

    /**
     * 添加商品数量记录信息
     *
     * @param param
     */
    void saveProModifyRecordNum(ParamProductSave param);
}
