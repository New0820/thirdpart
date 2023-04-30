package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantNums;
import com.example.temp.entity.pro.ProBrandSeries;
import com.example.temp.mapper.pro.ProBrandSeriesMapper;
import com.example.temp.service.pro.ProBrandSeriesService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 商品系列表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:58
 */
@Service
public class ProBrandSeriesServiceImpl extends ServiceImpl<ProBrandSeriesMapper, ProBrandSeries> implements ProBrandSeriesService {

    @Resource
    private ProBrandSeriesMapper proBrandSeriesMapper;


    /**
     * 根据品牌id和系列id查询
     * @param seriesId
     * @param brandId
     * @return
     */
    @Override
    public ProBrandSeries getProBrandSeriesByIdOrBrandId(Integer seriesId, Integer brandId) {
        LambdaQueryWrapper<ProBrandSeries> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProBrandSeries::getId,seriesId);
        if (!LocalUtils.isEmptyAndNull(brandId)) {
            queryWrapper.eq(ProBrandSeries::getFkProBrandId, brandId);
        }
        queryWrapper.eq(ProBrandSeries::getState, ConstantNums.ONE);
        queryWrapper.eq(ProBrandSeries::getDel,ConstantNums.ONE);
        ProBrandSeries proBrandSeries = proBrandSeriesMapper.selectOne(queryWrapper);
        if (LocalUtils.isEmptyAndNull(proBrandSeries)){
            //todo 提示系列不存在
        }
        return proBrandSeries;
    }
}
