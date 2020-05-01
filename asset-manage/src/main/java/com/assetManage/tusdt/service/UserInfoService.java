package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.model.User;
import com.assetManage.tusdt.model.bo.RegisterUserBO;
import com.assetManage.tusdt.model.bo.UserDetailBO;
import com.assetManage.tusdt.model.bo.UserListBO;
import com.assetManage.tusdt.model.bo.UserLoginBO;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-01-16
 * Time: 15:35
 */
public interface UserInfoService {

    List<UserListBO> getUserList(String userName);

    /**
     * 新建用户
     *
     * @param user
     * @return
     */
    ResponseData<String> addUser(User user, Integer userId);

    /**
     * 个人中心
     *
     * @param id
     * @return
     */
    ResponseData<UserDetailBO> getPersonnelDetail(Integer id);

    /**
     * 获取用户详细信息
     *
     * @param id
     * @return
     */
    ResponseData<User> getUserDetail(Integer id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    ResponseData<String> deleteUser(Integer id, Integer adminId);


    /**
     * 改变用户状态
     *
     * @param id
     * @param status
     * @return
     */
    ResponseData<String> changeUserStatus(Integer id, Integer status);

    /**
     * 编辑用户资料
     *
     * @param user
     * @return
     */
    ResponseData<String> modifyUserInfo(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    ResponseData<String> registerUser(User user);

    /**
     * 用户自己修改密码
     *
     * @param registerUserBO
     * @return
     */
    ResponseData<String> changeUserInfoByMyself(RegisterUserBO registerUserBO);

    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    ResponseData<UserLoginBO> login(String email, String password);


}
