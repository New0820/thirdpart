package com.example.temp.service.fin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.fin.FinFundRecord;
import com.example.temp.mapper.fin.FinFundRecordMapper;
import com.example.temp.service.fin.FinFundRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 资金流水记录表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:32:50
 */
@Service
@Transactional
public class FinFundRecordServiceImpl extends ServiceImpl<FinFundRecordMapper, FinFundRecord> implements FinFundRecordService {


}
