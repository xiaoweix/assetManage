package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.service.DataMapService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(protocols = "http,https", tags = {"dataMap"}, value = "/asset_manage/dataMap",description = "地图信息管理")
@RestController
@RequestMapping(value = "/asset_manage/dataMap")
public class DataMapController {

    @Resource
    private DataMapService dataMapService;


}
