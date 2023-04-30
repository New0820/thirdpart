package com.example.temp.service.pro.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.pro.ProDetail;
import com.example.temp.mapper.pro.ProDetailMapper;
import com.example.temp.service.pro.ProDetailService;
import org.springframework.stereotype.Service;


/**
 * 产品详情表;该表和(pro_product)一对一关系 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 11:47:28
 */
@Service
public class ProDetailServiceImpl extends ServiceImpl<ProDetailMapper, ProDetail> implements ProDetailService {


}
