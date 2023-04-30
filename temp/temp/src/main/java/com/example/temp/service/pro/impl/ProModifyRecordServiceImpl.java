package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.entity.pro.ProModifyRecord;
import com.example.temp.enums.pro.EnumProModifyRecordState;
import com.example.temp.enums.pro.EnumProModifyRecordType;
import com.example.temp.enums.shp.EnumShpSource;
import com.example.temp.mapper.pro.ProModifyRecordMapper;
import com.example.temp.param.pro.ParamProductSave;
import com.example.temp.service.pro.ProModifyRecordService;
import com.example.temp.service.pro.ProProductService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品修改记录表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:34:56
 */
@Service
public class ProModifyRecordServiceImpl extends ServiceImpl<ProModifyRecordMapper, ProModifyRecord> implements ProModifyRecordService {

    @Resource
    private ProProductService proProductService;

    /**
     * 添加商品操作记录
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProModifyRecord(ParamProductSave param) {
        if (!LocalUtils.isEmptyAndNull(param)) {
            List<ProModifyRecord> proModifyRecords = new ArrayList<>();
            ProModifyRecord proModifyRecord = new ProModifyRecord();
            proModifyRecord.setFkShpShopId(param.getShopId());
            proModifyRecord.setFkShpUserId(param.getUserId());
            proModifyRecord.setFkProProductId(param.getProId());
            proModifyRecord.setType(EnumProModifyRecordType.UPLOAD.getCode());
            proModifyRecord.setAttributeName(EnumProModifyRecordState.SAVE_STORE.getMsg());
            proModifyRecord.setSource(EnumShpSource.SHEBAOBAO.getMsg());
            String beforeValue = proProductService.getBeforeValue(param);
            proModifyRecord.setBeforeValue(param.getName() + "商品信息：" + beforeValue);
            proModifyRecord.setAfterValue(beforeValue);
            proModifyRecord.setInsertTime(new Date());
            proModifyRecords.add(proModifyRecord);
            ProModifyRecord proModifyRecordNum = new ProModifyRecord();
            proModifyRecordNum.setFkShpShopId(param.getShopId());
            proModifyRecordNum.setFkShpUserId(param.getUserId());
            proModifyRecordNum.setFkProProductId(param.getProId());
            proModifyRecordNum.setType(EnumProModifyRecordType.UPLOAD.getCode());
            proModifyRecordNum.setAttributeName(EnumProModifyRecordState.SAVE_UPLOAD_NUM.getMsg());
            proModifyRecordNum.setSource(EnumShpSource.SHEBAOBAO.getMsg());
            proModifyRecordNum.setBeforeValue(ConstantCommon.ZERO);
            proModifyRecordNum.setAfterValue(ConstantCommon.ONE);
            proModifyRecordNum.setInsertTime(new Date());
            proModifyRecords.add(proModifyRecordNum);
            saveBatch(proModifyRecords);
        }
    }
}
