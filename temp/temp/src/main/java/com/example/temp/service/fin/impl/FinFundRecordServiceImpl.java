package com.example.temp.service.fin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.fin.FinFundRecord;
import com.example.temp.mapper.fin.FinFundRecordMapper;
import com.example.temp.service.fin.FinFundRecordService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 资金流水记录表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:32:50
 */
@Service
public class FinFundRecordServiceImpl extends ServiceImpl<FinFundRecordMapper, FinFundRecord> implements FinFundRecordService {


    /**
     * 保存资金流水信息
     *
     * @param finFundRecords
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFundRecords(List<FinFundRecord> finFundRecords) {
        if (!LocalUtils.isEmptyAndNull(finFundRecords)) {
            saveBatch(finFundRecords);
        }
    }
}
