package com.example.temp.service.fin.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.entity.fin.FinBill;
import com.example.temp.entity.fin.FinFundRecord;
import com.example.temp.enums.fin.EnumFinShopRecordInoutType;
import com.example.temp.mapper.fin.FinBillMapper;
import com.example.temp.param.fin.ParamFundRecordSave;
import com.example.temp.service.fin.FinBillService;
import com.example.temp.service.fin.FinFundRecordService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 帐单表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:33:37
 */
@Service
public class FinBillServiceImpl extends ServiceImpl<FinBillMapper, FinBill> implements FinBillService {

    @Resource
    private FinBillMapper finBillMapper;

    @Resource
    private FinFundRecordService finFundRecordService;

    /**
     * 新增商品添加账单记录
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProductFundRecord(ParamFundRecordSave param) {
        LambdaQueryWrapper<FinBill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(FinBill::getState, ConstantNums.ZERO);
        queryWrapper.eq(FinBill::getFkShpShopId, param.getShopId());
        List<FinBill> finBills = finBillMapper.selectList(queryWrapper);
        if (!LocalUtils.isEmptyAndNull(finBills)) {
            List<FinFundRecord> finFundRecords = new ArrayList<>();
            List<FinBill> bills = new ArrayList<>();
            for (FinBill finBill : finBills) {
                FinFundRecord finFundRecord = new FinFundRecord();
                finFundRecord.setFkFinBillId(finBill.getId());
                finFundRecord.setFkShpShopId(param.getShopId());
                finFundRecord.setFundType(param.getFundType());
                finFundRecord.setWay(ConstantCommon.THIRTY_STR);
                finFundRecord.setInsertTime(new Date());
                finFundRecord.setInsertAdmin(param.getUserId());
                finFundRecord.setFinClassifyName(param.getFinClassifyName());
                if (EnumFinShopRecordInoutType.OUT.getCode().equals(param.getState())) {
                    finFundRecord.setState(ConstantCommon.ONE);
                }
                if (EnumFinShopRecordInoutType.IN.getCode().equals(param.getState())) {
                    finFundRecord.setState(ConstantCommon.ZERO);
                }
                if (!finBill.getTypes().contains(param.getAttributeCode()) && !"remove".equals(param.getAttributeCode())) {
                    return;
                }
                //入库商品
                if (ConstantCommon.TEN.equals(param.getFundType())) {
                    BigDecimal allMoney = new BigDecimal(param.getMoney()).multiply(new BigDecimal(param.getCount()));
                    finFundRecord.setMoney(allMoney);
                    //剩余金额 原有金额-进货金额
                    BigDecimal stayMoney = new BigDecimal(ConstantNums.ZERO);
                    if (ConstantCommon.ONE.equals(finFundRecord.getState())) {
                        stayMoney = finBill.getMoney().subtract(allMoney);
                    }
                    if (ConstantCommon.ZERO.equals(finFundRecord.getState())) {
                        stayMoney = finBill.getMoney().add(allMoney);
                    }
                    finBill.setMoney(stayMoney);
                    finFundRecords.add(finFundRecord);
                    bills.add(finBill);
                }
            }
            if (finFundRecords.size() > ConstantNums.ZERO) {
                //保存资金流水信息
                finFundRecordService.saveFundRecords(finFundRecords);
            }
            if (bills.size() > ConstantNums.ZERO) {
                saveOrUpdateBatch(bills);
            }
        }
    }
}
