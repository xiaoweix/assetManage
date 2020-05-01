package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.model.bo.WarehouseBO;
import com.assetManage.tusdt.service.AssetApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:31
 */
@Api(protocols = "http,https", tags = {"AssetApply"}, value = "/asset_manage/apply",description = "申请信息相关")
@RestController
@RequestMapping(value = "/asset_manage/apply")
public class AssetApplyController {

    @Resource
    AssetApplyService assetApplyService;


    @ApiOperation(value = "提交申请", notes = "提交资产有关申请，注意此接口借用、使用、申领、反馈通用")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "提交成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/postApply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> postApply(HttpServletRequest request,@RequestBody AssetApply assetApply) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_TEACHER && assetApply.getType().equals(CommonConstant.ASSET_USE_TYPE_GET)) {
            responseData.setError("学生不能申领");
            return responseData;
        }
        responseData = assetApplyService.postApply(assetApply);
        return responseData;
    }

    @ApiOperation(value = "申请列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetLogList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<AssetApplyListBO>> getAssetLogList(HttpServletRequest request,
                                                                @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                                @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                                @RequestParam(value = "assetId",required = false) Integer assetId,
                                                                @RequestParam(value = "userName",required = false) String userName,
                                                                @RequestParam(value = "status",required = false) Integer status,
                                                                @RequestParam(value = "telephone",required = false) String telephone,
                                                                @RequestParam(value = "jobLevel",required = false) Integer jobLevel) {

        ResponseData<List<AssetApplyListBO>> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        List<AssetApplyListBO> assetLogListBOList = assetApplyService.getAssetApplyList(currPage, pageSize, assetId, userName, status, telephone, jobLevel);
        PageHelper.startPage(currPage,pageSize);
        PageInfo<AssetApplyListBO> pageInfo = new PageInfo<>(assetLogListBOList);
        responseData.set("获取成功",pageInfo.getList());
        return responseData;
    }

    @ApiOperation(value = "同意申请", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "操作成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/agreeApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> agreeApply(HttpServletRequest request,
                                                              @RequestParam(value = "applyId",required = true) Integer applyId) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = assetApplyService.agreeApply(applyId);
        return responseData;
    }

    @ApiOperation(value = "拒绝申请", notes = "")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "操作成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/disagreeApply", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> disagreeApply(HttpServletRequest request,
                                           @RequestParam(value = "applyId",required = true) Integer applyId) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = assetApplyService.refuseApply(applyId);
        return responseData;
    }
}
