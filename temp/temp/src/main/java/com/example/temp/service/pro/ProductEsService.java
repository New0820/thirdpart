package com.example.temp.service.pro;


import com.example.temp.entity.pro.ProDetail;
import com.example.temp.entity.pro.ProProduct;


/**
 * create by: CM
 * description: 商品数据汇总及es逻辑接口
 * create time: 2022/7/19 17:15
 *
 * @author cm
 */
public interface ProductEsService {

    /**
     * 修改es中的商品信息
     * @param pro 商品信息
     * @param proDetail 详情信息
     * @param source save/update
     */
    void jointProInfo(ProProduct pro, ProDetail proDetail,String source);

}
