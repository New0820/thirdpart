package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.constant.ConstantCommon;
import com.example.temp.constant.ConstantNums;
import com.example.temp.entity.pro.ProBrand;
import com.example.temp.mapper.pro.ProBrandMapper;
import com.example.temp.service.pro.ProBrandService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 商品品牌表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:30
 */
@Service
public class ProBrandServiceImpl extends ServiceImpl<ProBrandMapper, ProBrand> implements ProBrandService {

    @Resource
    private ProBrandMapper proBrandMapper;

    /**
     * 根据品牌id查询信息
     * @param id
     * @return
     */
    @Override
    public ProBrand getProBrandById(Integer id) {
        LambdaQueryWrapper<ProBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProBrand::getId,id);
        queryWrapper.eq(ProBrand::getState, ConstantNums.ONE);
        queryWrapper.eq(ProBrand::getDel,ConstantNums.ONE);
        ProBrand proBrand = proBrandMapper.selectOne(queryWrapper);
        if (LocalUtils.isEmptyAndNull(proBrand)){
            //todo 提示改品牌不存在
        }
        return proBrand;
    }
}
