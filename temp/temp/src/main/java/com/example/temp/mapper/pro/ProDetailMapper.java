package com.example.temp.mapper.pro;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.temp.entity.pro.ProDetail;
import org.springframework.stereotype.Repository;


/**
 * 产品详情表;该表和(pro_product)一对一关系 dao
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:28
 */
@Repository
public interface ProDetailMapper extends BaseMapper<ProDetail> {


}
