package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.AssetMap;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.MapListBO;
import com.assetManage.tusdt.service.DataMapService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(protocols = "http,https", tags = {"dataMap"}, value = "/asset_manage/dataMap",description = "地图信息管理")
@RestController
@RequestMapping(value = "/asset_manage/dataMap")
public class DataMapController {

    @Resource
    private DataMapService dataMapService;

    @ApiOperation(value = "添加一张地图", notes = "添加地图")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addAssetMap", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addAssetMap(HttpServletRequest request, @RequestBody AssetMap assetMap) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = dataMapService.addMap(assetMap);

        return responseData;
    }
    @ApiOperation(value = "获取地图下拉框", notes = "获取类型列表")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetMapList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<MapListBO>> assetTypeList(HttpServletRequest request) {

        ResponseData<List<MapListBO>> responseData = new ResponseData<>();
        List<MapListBO> mapListBOList = dataMapService.mapList();
        if(mapListBOList == null) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",mapListBOList);
        return responseData;
    }
    @ApiOperation(value = "获取所有地图资料", notes = "获取地图资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetAllMapList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetMap>> assetAllMapList(HttpServletRequest request) {

        ResponseData<List<AssetMap>> responseData = new ResponseData<>();
        List<AssetMap> mapListBOList = dataMapService.getAllMap();
        if(mapListBOList == null) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",mapListBOList);
        return responseData;
    }

    @ApiOperation(value = "查询该块地图有哪些资源", notes = "获取资源")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getMapAsset", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetListBO>> getMapAsset(HttpServletRequest request,@RequestParam(value = "mapId",required = false) Integer mapId) {

        ResponseData<List<AssetListBO>> responseData = new ResponseData<>();
        List<AssetListBO> mapListBOList = dataMapService.getByMapId(mapId);
        if(mapListBOList == null) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",mapListBOList);
        return responseData;
    }
    @ApiOperation(value = "查询该块地图有哪些资源", notes = "获取资源")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getAssetLocation", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<Integer>> getAssetLocation(HttpServletRequest request,
                                                        @RequestParam(value = "assetId",required = false) Integer assetId,
                                                        @RequestParam(value = "assetName",required = false) String assetName) {

        ResponseData<List<Integer>> responseData = new ResponseData<>();
        if (assetId == null && assetName == null) {
            responseData.setError("查询条件不能为空");
            return responseData;
        }
        List<Integer> mapListBOList = dataMapService.getAssetLocation(assetId, assetName);
        if(mapListBOList == null) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",mapListBOList);
        return responseData;
    }
}
