package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.OperLogListBO;
import com.assetManage.tusdt.service.AssetLogInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-15
 * Time: 23:52
 */
@Api(protocols = "http,https", tags = {"logInfo"}, value = "/asset_manage/logInfo",description = "操作日志接口")
@RestController
@RequestMapping(value = "/asset_manage/logInfo")
public class AssetLogInfoController {

    @Resource
    private AssetLogInfoService assetLogInfoService;



    @ApiOperation(value = "获取系统操作日志", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/assetLogList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<OperLogListBO>> getAssetLogList(HttpServletRequest request,
                                                             @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                             @RequestParam(value = "userName",required = false) String userName,
                                                             @RequestParam(value = "dataFrom",required = false) String dataFrom,
                                                             @RequestParam(value = "dataEnd",required = false) String dataEnd) {

        ResponseData<List<OperLogListBO>> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        List<OperLogListBO> operLogListBOS = assetLogInfoService.getOperLog(userName, dataFrom, dataEnd);
        if(operLogListBOS == null) {
            responseData.setError("获取失败");
            return responseData;
        }
        PageHelper.startPage(currPage,pageSize);
        PageInfo<OperLogListBO> pageInfo = new PageInfo<>(operLogListBOS);
        responseData.set("获取成功",pageInfo.getList());
        return responseData;
    }

}
