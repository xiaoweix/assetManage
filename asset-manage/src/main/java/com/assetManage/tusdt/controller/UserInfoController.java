package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.model.User;
import com.assetManage.tusdt.model.bo.OperLogListBO;
import com.assetManage.tusdt.model.bo.UserDetailBO;
import com.assetManage.tusdt.model.bo.UserListBO;
import com.assetManage.tusdt.service.UserInfoService;
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
 * Date: 2020-01-15
 * Time: 19:51
 */
@Api(protocols = "http,https", tags = {"UserManage"}, value = "/asset_manage/user",description = "用户信息管理")
@RestController
@RequestMapping(value = "/asset_manage/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "获取用户列表", notes = "参数name可以模糊查询")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<UserListBO>> getUserList(HttpServletRequest request,
                                                @RequestParam(name = "currPage", required = false, defaultValue = "1") Integer currPage,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                @RequestParam(value = "userId",required = false) Integer userId,
                                                @RequestParam(value = "userName",required = false) String userName,
                                                @RequestParam(value = "status",required = false) Integer status,
                                                @RequestParam(value = "telephone",required = false) String telephone,
                                                @RequestParam(value = "jobLevel",required = false) Integer jobLevel) {

        ResponseData<List<UserListBO>> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足，仅管理员可见");
            return responseData;
        }
        List<UserListBO> userList = userInfoService.getUserList(userName);
        if(userList == null || userList.size() == 0) {
            responseData.setError("获取失败");
            return responseData;
        }

        PageHelper.startPage(currPage,pageSize);
        PageInfo<UserListBO> pageInfo = new PageInfo<>(userList);
        responseData.set("获取成功",pageInfo.getList());
        return responseData;
    }


    @ApiOperation(value = "新增一个用户", notes = "增加用户")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "添加成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> addUser(HttpServletRequest request, @RequestBody User user) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        Integer userId = (Integer) request.getAttribute("id");
        if(rank < CommonConstant.JOB_LEVEL_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = userInfoService.addUser(user,userId);
        return responseData;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "删除成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/removeUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<String> removeUser(HttpServletRequest request,
                                                @RequestParam(value = "userId",required = true) Integer userId) {

        ResponseData<String> responseData = new ResponseData<>();
        Integer adminId = (Integer) request.getAttribute("id");
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_SUPER_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = userInfoService.deleteUser(userId,adminId);
        return responseData;
    }

    @ApiOperation(value = "编辑用户资料", notes = "编辑用户资料")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "修改成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> modifyUser(HttpServletRequest request,@RequestBody User user) {

        ResponseData<String> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_SUPER_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = userInfoService.modifyUserInfo(user);
        return responseData;
    }

    @ApiOperation(value = "用户详情", notes = "用户详情")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "获取成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<User> userDetail(HttpServletRequest request,
                                           @RequestParam(value = "userId",required = true) Integer userId) {

        ResponseData<User> responseData = new ResponseData<>();
        int rank = (int) request.getAttribute("jobLevel");
        if(rank < CommonConstant.JOB_LEVEL_SUPER_ADMIN) {
            responseData.setError("权限不足");
            return responseData;
        }
        responseData = userInfoService.getUserDetail(userId);
        return responseData;
    }



    @ApiOperation(value = "个人中心", notes = "个人中心")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "查询成功"),})
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "header", name = "token", dataType = "String", required = true, value = "token"),
            }
    )
    @RequestMapping(value = "/personalZone", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<UserDetailBO> personalZone(HttpServletRequest request) {

        ResponseData<UserDetailBO> responseData = new ResponseData<>();
        Integer userId = (Integer) request.getAttribute("id");
        return userInfoService.getPersonnelDetail(userId);
    }
}

