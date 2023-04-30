package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantNums;
import com.example.temp.entity.pro.ProBrandModel;
import com.example.temp.entity.pro.ProBrandSeries;
import com.example.temp.mapper.pro.ProBrandModelMapper;
import com.example.temp.mapper.pro.ProBrandSeriesMapper;
import com.example.temp.service.pro.ProBrandModelService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 商品型号表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:42
 */
@Service
public class ProBrandModelServiceImpl extends ServiceImpl<ProBrandModelMapper, ProBrandModel> implements ProBrandModelService {

    @Resource
    private ProBrandModelMapper proBrandModelMapper;

    /**
     * 根据系列idOr型号id
     *
     * @param seriesId 系列id
     * @param id  型号id
     * @return
     */
    @Override
    public ProBrandModel getByBrandModelByIdOrSeriesId(Integer seriesId, Integer id) {
        LambdaQueryWrapper<ProBrandModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProBrandModel::getId,id);
        if (!LocalUtils.isEmptyAndNull(seriesId)) {
            queryWrapper.eq(ProBrandModel::getFkProBrandSeriesId, seriesId);
        }
        queryWrapper.eq(ProBrandModel::getState, ConstantNums.ONE);
        queryWrapper.eq(ProBrandModel::getDel,ConstantNums.ONE);
        ProBrandModel proBrandModel = proBrandModelMapper.selectOne(queryWrapper);
        if (LocalUtils.isEmptyAndNull(proBrandModel)){
            //todo 提示系列不存在
        }
        return proBrandModel;
    }
}
