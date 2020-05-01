package com.assetManage.tusdt.controller;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.model.User;
import com.assetManage.tusdt.model.bo.RegisterUserBO;
import com.assetManage.tusdt.model.bo.UserLoginBO;
import com.assetManage.tusdt.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 16:17
 */
@Api(protocols = "http,https", tags = {"User"}, value = "/asset_manage/user",description = "用户登录注册")
@RestController
public class LoginRegisterController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "注册", notes = "注册")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "注册信息提交成功"),})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> register(@RequestBody User user) {

        ResponseData<String> responseData;
        responseData = userInfoService.registerUser(user);
        return responseData;
    }

    @ApiOperation(value = "登录", notes = "登录")
    @ApiResponses({@ApiResponse(code = Response.OK, message = "注册信息提交成功"),})
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<UserLoginBO> login(@RequestParam(value = "email",required = true) String email,
                                           @RequestParam(value = "password",required = true) String password) {


        return userInfoService.login(email,password);
    }

}
