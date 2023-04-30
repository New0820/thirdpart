package com.example.temp.controller.pro;

import com.example.temp.common.base.BaseController;
import com.example.temp.param.ParamSaveProduct;
import com.example.temp.service.pro.ProProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author monkey king
 * @Date 2019/12/01 4:07
 */
@Slf4j
@RestController
@Api(tags = {"商品模块"})
@RequestMapping(value = "/api/pro")
public class ProProductController extends BaseController {

    @Autowired
    private ProProductService proProductService;

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    @ApiOperation(value = "添加商品", notes = "添加商品", httpMethod = "POST")
    public void saveProduct(@RequestBody ParamSaveProduct param, HttpServletRequest request) {
        param.setUserId(getUserId());
        param.setShopId(getShopId());
        param.setRequest(request);
        proProductService.saveProduct(param);
    }
}
