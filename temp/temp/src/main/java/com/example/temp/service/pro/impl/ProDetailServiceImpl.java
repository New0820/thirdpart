package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.pro.ProDetail;
import com.example.temp.mapper.pro.ProDetailMapper;
import com.example.temp.service.pro.ProDetailService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 产品详情表;该表和(pro_product)一对一关系 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:28
 */
@Service
public class ProDetailServiceImpl extends ServiceImpl<ProDetailMapper, ProDetail> implements ProDetailService {

    @Resource
    private ProDetailMapper proDetailMapper;

    /**
     * 根据商品id插叙商品详情信息
     *
     * @param proId
     * @return
     */
    @Override
    public ProDetail getProDetailByProId(Integer proId) {
        LambdaQueryWrapper<ProDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ProDetail::getFkProProductId, proId);
        ProDetail proDetail = proDetailMapper.selectOne(lambdaQueryWrapper);
        if (LocalUtils.isEmptyAndNull(proDetail)) {
            //todo 不存在
        }
        return proDetail;
    }

    /**
     * 添加商品详情信息
     *
     * @param proDetail
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProDetail(ProDetail proDetail) {
        save(proDetail);
    }

    /**
     * 根据id修改商品详情
     * @param proDetail
     */
    @Override
    public void updateProDetail(ProDetail proDetail) {
        updateById(proDetail);
    }
}
