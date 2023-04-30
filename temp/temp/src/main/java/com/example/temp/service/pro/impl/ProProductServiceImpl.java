package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.temp.entity.param.ParamSaveProduct;
import com.example.temp.entity.pro.ProProduct;
import com.example.temp.mapper.pro.ProProductMapper;
import com.example.temp.service.pro.ProProductService;
import com.example.temp.util.LocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 产品表;该表和(pro_detail)一对一关系 serverImplProDetailMapper
 * ProProductMapper
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:17
 */
@Service
public class ProProductServiceImpl implements ProProductService {

    @Resource
    private ProProductMapper proProductMapper;

    /**
     * 添加商品
     *
     * @param param
     */
    @Override
    public void saveProduct(ParamSaveProduct param) {

    }


    /**
     * 根据商品id获取详情信息
     * @param proId
     * @return
     */
    public ProProduct getProductById(Integer proId){
        ProProduct proProduct = proProductMapper.selectById(proId);
        if (LocalUtils.isEmptyAndNull(proProduct)){
            //todo "提示商品不存在";
        }
        return proProduct;
    }
}
