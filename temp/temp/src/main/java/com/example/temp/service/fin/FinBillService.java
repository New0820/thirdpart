package com.example.temp.service.fin;

import com.example.temp.param.fin.ParamFundRecordSave;

/**
 * 帐单表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:33:37
 */
public interface FinBillService {


    /**
     * 新增商品添加账单记录
     *
     * @param param
     */
    void saveProductFundRecord(ParamFundRecordSave param);
}
