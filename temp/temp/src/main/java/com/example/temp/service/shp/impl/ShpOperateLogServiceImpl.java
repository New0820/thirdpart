package com.example.temp.service.shp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.temp.entity.shp.ShpOperateLog;
import com.example.temp.entity.shp.ShpUserShopRef;
import com.example.temp.mapper.shp.ShpOperateLogMapper;
import com.example.temp.param.shp.ParamShpOperateLogSave;
import com.example.temp.service.shp.ShpOperateLogService;
import com.example.temp.service.shp.ShpUserShopRefService;
import com.example.temp.util.DateUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 店铺操作日志表 serverImpl
 *
 * @author taoqimin
 * @Date 2023-04-30 17:31:50
 */
@Service
public class ShpOperateLogServiceImpl extends ServiceImpl<ShpOperateLogMapper, ShpOperateLog> implements ShpOperateLogService {

    @Resource
    private ShpUserShopRefService shpUserShopRefService;

    @Resource
    private ShpOperateLogMapper shpOperateLogMapper;

    @Resource

    private Gson gson;

    /**
     * 添加店铺操作日志
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveShpOperateLog(ParamShpOperateLogSave param) {
        ShpOperateLog shpOperateLog = new ShpOperateLog();
        shpOperateLog.setOperateAfter(param.getOperateAfter());
        //店铺ID
        Integer shopId = param.getShopId();
        shpOperateLog.setFkShpShopId(shopId);
        //操作用户ID
        Integer operateUserId = param.getOperateUserId();
        shpOperateLog.setOperateUserId(operateUserId);
        shpOperateLog.setShopName(param.getShopName());
        //用户所在店铺名称
        ShpUserShopRef shpUserShopRef = shpUserShopRefService.getByShopIdAndUserId(shopId, operateUserId);
        shpOperateLog.setOperateUserShopName(shpUserShopRef.getName());
        //模块名称
        shpOperateLog.setModuleName(param.getModuleName());
        //操作业务类型名称
        shpOperateLog.setOperateTypeName(param.getOperateName());
        //操作内容
        shpOperateLog.setOperateContent(param.getOperateContent());
        //商品ID
        shpOperateLog.setFkProProductId(param.getProdId());
        //请求request
        HttpServletRequest request = param.getRequest();
        shpOperateLog.setRequestDomain(request.getLocalName());
        shpOperateLog.setRequestUri(request.getRequestURI());
        shpOperateLog.setRequestParam(gson.toJson(request.getParameterMap()));
        shpOperateLog.setRequestIp(request.getRemoteAddr());
        shpOperateLog.setRequestMethod(request.getMethod());
        //操作时间
        Date operateTime = new Date();
        shpOperateLog.setInsertTime(operateTime);
        shpOperateLog.setUpdateTime(operateTime);
        shpOperateLog.setOperateDate(DateUtil.formatShort(operateTime));
        shpOperateLog.setOperateTime(operateTime);
        shpOperateLogMapper.insert(shpOperateLog);
    }
}
