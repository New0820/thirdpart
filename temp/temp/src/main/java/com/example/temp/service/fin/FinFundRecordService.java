package com.example.temp.service.fin;

import com.example.temp.entity.fin.FinFundRecord;

import java.util.List;

/**
 * 资金流水记录表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:32:50
 */
public interface FinFundRecordService {


    /**
     * 保存资金流水信息
     *
     * @param finFundRecords
     */
    void saveFundRecords(List<FinFundRecord> finFundRecords);
}
