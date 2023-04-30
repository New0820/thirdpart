package com.example.temp.service.fin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.fin.FinBill;
import com.example.temp.mapper.fin.FinBillMapper;
import com.example.temp.service.fin.FinBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 帐单表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:33:37
 */
@Service
@Transactional
public class FinBillServiceImpl extends ServiceImpl<FinBillMapper, FinBill> implements FinBillService {


}
