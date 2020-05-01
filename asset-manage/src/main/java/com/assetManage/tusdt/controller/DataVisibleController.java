package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.bo.IndexDataBO;
import com.assetManage.tusdt.service.DataVisibleService;
import com.assetManage.tusdt.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:06
 */
@Api(protocols = "http,https", tags = {"dataVisible"}, value = "/asset_manage/dataVisible",description = "数据可视化")
@RestController
@RequestMapping(value = "/asset_manage/dataVisible")
public class DataVisibleController {

    @Resource
    DataVisibleService dataVisibleService;

    @ApiOperation(value = "首页的数据可视化", notes = "首页的数据可视化")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResponseData<IndexDataBO> index(HttpServletRequest request) {
        ResponseData<IndexDataBO> result = dataVisibleService.getIndexData();
        return result;
    }



}
