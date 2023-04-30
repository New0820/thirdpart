package com.example.temp.mapper.pro;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.temp.entity.entity.pro.ProProduct;
import org.springframework.stereotype.Repository;


/**
 * 产品表;该表和(pro_detail)一对一关系 dao
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:17
 */
@Repository
public interface ProProductMapper extends BaseMapper<ProProduct> {


}
