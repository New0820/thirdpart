package com.example.temp.service.pro;

import com.example.temp.entity.pro.ProBrandSeries;

/**
 * 商品系列表 service
 *
 * @author taoqimin
 * @Date 2023-04-30 17:30:58
 */
public interface ProBrandSeriesService {

    /**
     * 根据系列id和品牌id查询系列
     * @param seriesId
     * @param brandId
     * @return
     */
    ProBrandSeries getProBrandSeriesByIdOrBrandId(Integer seriesId,Integer brandId);

}
