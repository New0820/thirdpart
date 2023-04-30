package com.example.temp.service.pro;


import com.example.temp.entity.pro.ProDetail;

/**
 * 产品详情表;该表和(pro_product)一对一关系 service
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:28
 */
public interface ProDetailService {


    /**
     * 根据商品id查询商品详情信息
     * @param proId
     * @return
     */
    ProDetail getProDetailByProId(Integer proId);
}
