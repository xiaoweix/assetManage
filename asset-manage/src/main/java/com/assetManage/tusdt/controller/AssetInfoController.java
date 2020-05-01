package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.AssetUseHistoryBO;
import com.assetManage.tusdt.service.AssetInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Description:
 * Author: xxw
 * Date: 2020-01-20
 * Time: 19:44
 */
@Api(protocols = "http,https", tags = {"Asset"}, value = "/asset_manage/asset",description = "资源管理管理")
@RestController
@RequestMapping(value = "/asset_manage/asset")
public class AssetInfoController {

    @Autowired
    AssetInfoService assetInfoService;


    @ApiOperation(value = "资源入库", notes = "增加资源")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addAsset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addAsset(HttpServletRequest request,@RequestBody AssetInfo assetInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        Integer userId = (Integer) request.getAttribute("id");
        responseData = assetInfoService.addAsset(userId,assetInfo);
        return responseData;
    }

    @ApiOperation(value = "获取资源列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetListBO>> getUserList(HttpServletRequest request,
                                                             @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                             @RequestParam(value = "assetName",required = false) String assetName,
                                                             @RequestParam(value = "assetId",required = false) Integer assetId,
                                                             @RequestParam(value = "warehouseName",required = false) String warehouseName,
                                                             @RequestParam(value = "status",required = false) Integer status
                                                ) {
        ResponseData<List<AssetListBO>> responseData = new ResponseData<>();
        Integer useType = null;
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            useType = CommonConstant.ASSET_USE_TYPE_USE;
        }
        List<AssetListBO> assetList = assetInfoService.getAssetList(currPage, pageSize, assetId, assetName, warehouseName, status, useType);
        if(assetList == null ) {
            responseData.setError("获取失败");
            return responseData;
        }
        PageHelper.startPage(currPage,pageSize);
        PageInfo<AssetListBO> pageInfo = new PageInfo<>(assetList);
        responseData.set("获取成功",pageInfo.getList());
        return responseData;
    }

    @ApiOperation(value = "获取资源详情", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<AssetInfo> assetDetail(@RequestParam(name = "id", required = true, defaultValue = "1") Integer id) {

        ResponseData<AssetInfo> responseData = assetInfoService.getAssetInfoDetail(id);
        return responseData;
    }

    @ApiOperation(value = "修改、编辑资源", notes = "修改资源")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyAsset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyAsset(HttpServletRequest request,@RequestBody AssetInfo assetInfo) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        Integer userId = (Integer) request.getAttribute("id");
        responseData = assetInfoService.modifyAsset(userId,assetInfo);
        return responseData;
    }

    @ApiOperation(value = "获取资源列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/getHistoryList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetUseHistoryBO>> getHistoryList(HttpServletRequest request,
                                                             @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                             @RequestParam(value = "assetName",required = false) String assetName,
                                                             @RequestParam(value = "assetId",required = false) Integer assetId,
                                                             @RequestParam(value = "userName",required = false) String userName,
                                                             @RequestParam(value = "type",required = false) Integer type
    ) {
        ResponseData<List<AssetUseHistoryBO>> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        List<AssetUseHistoryBO> assetList = assetInfoService.getAssetHistory(currPage, pageSize, assetId, assetName, userName, type);
        if(assetList == null ) {
            responseData.setError("获取失败");
        }
        PageHelper.startPage(currPage,pageSize);
        PageInfo<AssetUseHistoryBO> pageInfo = new PageInfo<>(assetList);
        responseData.set("获取成功",pageInfo.getList());
        return responseData;
    }
}
