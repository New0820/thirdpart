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

import javax.annotation.Resource;
import java.util.Date;


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
     * 添加商品操作记录信息
     *
     * @param param
     */
    @Override
    public void saveProModifyRecord(ParamProductSave param) {
        if (!LocalUtils.isEmptyAndNull(param)) {
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
            save(proModifyRecord);
        }
    }

    /**
     * 添加商品数量记录信息
     *
     * @param param
     */
    @Override
    public void saveProModifyRecordNum(ParamProductSave param) {
        if (!LocalUtils.isEmptyAndNull(param)) {
            ProModifyRecord proModifyRecord = new ProModifyRecord();
            proModifyRecord.setFkShpShopId(param.getShopId());
            proModifyRecord.setFkShpUserId(param.getUserId());
            proModifyRecord.setFkProProductId(param.getProId());
            proModifyRecord.setType(EnumProModifyRecordType.UPLOAD.getCode());
            proModifyRecord.setAttributeName(EnumProModifyRecordState.SAVE_UPLOAD_NUM.getMsg());
            proModifyRecord.setSource(EnumShpSource.SHEBAOBAO.getMsg());
            proModifyRecord.setBeforeValue(ConstantCommon.ZERO);
            proModifyRecord.setAfterValue(ConstantCommon.ONE);
            proModifyRecord.setInsertTime(new Date());
            save(proModifyRecord);
        }
    }
}
