package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.AssetType;
import com.assetManage.tusdt.service.AssetTypeService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 00:26
 */
@Api(protocols = "http,https", tags = {"AssetType"}, value = "/asset_manage/assetType",description = "资源类型管理")
@RestController
@RequestMapping(value = "/asset_manage/assetType")
public class AssetTypeController {


    @Resource
    private AssetTypeService assetTypeService;

    @ApiOperation(value = "新增一个标签", notes = "增加标签")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addAssetType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addAssetType(HttpServletRequest request, @RequestBody String assetName) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = assetTypeService.addAssetType(assetName);

        return responseData;
    }

    @ApiOperation(value = "获取类型列表", notes = "获取类型列表")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetTypeList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetType>> assetTypeList(HttpServletRequest request) {

        ResponseData<List<AssetType>> responseData = new ResponseData<>();
        List<AssetType> assetTypeList = assetTypeService.getAssetTypeList();
        if(assetTypeList == null) {
            responseData.setError("获取失败");
        }
        responseData.set("获取成功",assetTypeList);
        return responseData;
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeAssetType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeAssetType(HttpServletRequest request,
                                                         @RequestParam(value = "typeId",required = false) Integer typeId) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = assetTypeService.removeAssetType(typeId);
        return responseData;
    }
}
