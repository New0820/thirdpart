package com.example.temp.service.pro;

import com.example.temp.entity.param.ParamSaveProduct;
import com.example.temp.entity.param.ParamUpdateProduct;

/**
 * 产品表;该表和(pro_detail)一对一关系 service
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:17
 */
public interface ProProductService {

    /**
     * 添加商品
     *
     * @param param
     */
    void saveProduct(ParamSaveProduct param);


    /**
     * 修改商品
     * @param param
     */
    void updateProduct(ParamUpdateProduct param);
}
