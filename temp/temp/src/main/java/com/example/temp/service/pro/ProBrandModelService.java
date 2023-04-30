package com.example.temp.service.pro;

import com.example.temp.entity.pro.ProBrandModel;

/**
 * 商品型号表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:42
 */
public interface ProBrandModelService {


    /**
     * 根据品牌Or系列idOr型号id
     * @param seriesId 系列id
     * @param modelId 型号id
     * @return
     */
    ProBrandModel getByBrandModelByIdOrSeriesId(Integer seriesId,Integer modelId);

}
