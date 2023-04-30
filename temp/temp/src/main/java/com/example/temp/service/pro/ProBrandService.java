package com.example.temp.service.pro;

import com.example.temp.entity.pro.ProBrand;

/**
 * 商品品牌表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:30
 */
public interface ProBrandService {


    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    ProBrand getProBrandById(Integer id);

}
