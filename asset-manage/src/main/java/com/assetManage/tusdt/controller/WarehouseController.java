package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.Warehouse;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.WarehouseBO;
import com.assetManage.tusdt.service.WarehouseInfoService;
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
 * Date: 2020-02-09
 * Time: 13:36
 */
@Api(protocols = "http,https", tags = {"Repository"}, value = "/asset_manage/repository",description = "仓库管理")
@RestController
@RequestMapping(value = "/asset_manage/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseInfoService warehouseInfoService;


    @ApiOperation(value = "新建仓库", notes = "新建仓库")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addWarehouse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addAsset(HttpServletRequest request, @RequestBody Warehouse warehouse) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = warehouseInfoService.addWarehouse(userId, warehouse);
        return responseData;
    }


    @ApiOperation(value = "获取仓库列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/warehouseList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<WarehouseBO>> warehouseList(HttpServletRequest request,@RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                               @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                               @RequestParam(value = "warehouseName",required = false) String warehouseName,
                                                               @RequestParam(value = "assetId",required = false) Integer id,
                                                               @RequestParam(value = "address",required = false) String address
    ) {
        ResponseData<List<WarehouseBO>> responseData = new ResponseData<>();

        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        List<WarehouseBO> warehouseList = warehouseInfoService.getWarehouseList(currPage,pageSize,id,warehouseName,address);


        if(warehouseList == null ) {
            responseData.setError("获取失败");
            return responseData;
        } else {
            PageHelper.startPage(currPage,pageSize);
            PageInfo<WarehouseBO> pageInfo = new PageInfo<>(warehouseList);
            responseData.set("获取成功",pageInfo.getList());
        }
        return responseData;
    }

    @ApiOperation(value = "删除仓库", notes = "删除仓库")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeWarehouse", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeWarehouse(HttpServletRequest request,
                                                @RequestParam(value = "warehouseId",required = true) Integer warehouseId) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = warehouseInfoService.removeWarehouse(userId,warehouseId);
        return responseData;
    }

    @ApiOperation(value = "编辑仓库", notes = "编辑仓库")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyWarehouse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyWarehouse(HttpServletRequest request, @RequestBody Warehouse warehouse) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = warehouseInfoService.modifyWarehouse(userId,warehouse);
        return responseData;
    }

}
