package com.example.temp.service.shp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.entity.shp.ShpUser;
import com.example.temp.mapper.shp.ShpUserMapper;
import com.example.temp.service.shp.ShpUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 店铺管理员 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:55:41
 */
@Service
public class ShpUserServiceImpl extends ServiceImpl<ShpUserMapper, ShpUser> implements ShpUserService {

}
