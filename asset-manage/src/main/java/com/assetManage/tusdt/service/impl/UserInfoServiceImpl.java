package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetApplyMapper;
import com.assetManage.tusdt.dao.UserMapper;
import com.assetManage.tusdt.model.User;
import com.assetManage.tusdt.model.bo.*;
import com.assetManage.tusdt.service.AssetLogInfoService;
import com.assetManage.tusdt.service.EmailService;
import com.assetManage.tusdt.service.UserInfoService;
import com.assetManage.tusdt.utils.HashUtils;
import com.assetManage.tusdt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-01-16
 * Time: 15:40
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AssetApplyMapper assetApplyMapper;

    @Resource
    private AssetLogInfoService assetLogInfoService;

    @Resource
    private EmailService emailService;


    @Override
    public List<UserListBO> getUserList(Integer userId,String userName,Integer status,String telephone,Integer jobLevel) {
        return userMapper.selectByUserName(userId,userName,status,telephone,jobLevel);
    }

    @Override
    public ResponseData<String> addUser(User user , Integer userId) {
        ResponseData<String> responseData = new ResponseData<>();
        User checkUserEmail = userMapper.loginByEmail(user.getEmail());

        if(checkUserEmail != null) {
            responseData.setError("该用户邮箱已存在！");
            return responseData;
        }
        user.setPassword(HashUtils.hashEncrypt(user.getPassword(),CommonConstant.PASSWORD_HASH));
        user.setIsDelete(CommonConstant.DELETED_NO);
        user.setStatus(CommonConstant.USER_STATUS_NORMAL);
        Integer result = userMapper.insert(user);
        if (result == 1) {
            responseData.setOK("添加成功");
            assetLogInfoService.addOperLog(userId,String.format("新增用户 '%s' ",user.getUserName()));
        } else {
            responseData.setError("添加失败");
        }
        return responseData;
    }

    @Override
    public ResponseData<UserDetailBO> getPersonnelDetail(Integer id) {
        ResponseData<UserDetailBO> responseData = new ResponseData<>();
        UserDetailBO userDetailBO = userMapper.getPersonalDetail(id);
        userDetailBO.setAssetUseHistoryBOList(assetApplyMapper.getAssetUseHistory(null,null,null,null,id));
        responseData.setOK("获取成功",userDetailBO);
        return responseData;
    }

    @Override
    public ResponseData<User> getUserDetail(Integer id) {
        ResponseData<User> responseData = new ResponseData<>();
        User user = userMapper.selectUserDetail(id);
        responseData.setOK("获取成功",user);
        return responseData;
    }

    @Override
    public ResponseData<String> deleteUser(Integer id, Integer adminId) {
        ResponseData<String> responseData = new ResponseData<>();
        User user = userMapper.selectByPrimaryKey(id);
        user.setIsDelete(CommonConstant.DELETED_YES);
        userMapper.updateByPrimaryKeySelective(user);
        assetLogInfoService.addOperLog(adminId,String.format("删除用户 '%s' ",user.getUserName()));
        responseData.setOK("删除成功");
        return responseData;
    }

    @Override
    public ResponseData<String> changeUserStatus(Integer id, Integer status) {
        ResponseData<String> responseData = new ResponseData<>();
        User user = userMapper.selectByPrimaryKey(id);
        if(CommonConstant.USER_STATUS_NORMAL.equals(status)) {
            user.setStatus(CommonConstant.USER_STATUS_ABNORMAL);
        } else if(CommonConstant.USER_STATUS_ABNORMAL.equals(status)){
            user.setStatus(CommonConstant.USER_STATUS_NORMAL);
        }
        responseData.setOK("更改成功");
        return responseData;
    }

    @Override
    public ResponseData<String> modifyUserInfo(User user) {
        ResponseData<String> responseData = new ResponseData<>();
        User oldUser = userMapper.selectByPrimaryKey(user.getId());
        if (oldUser == null) {
            responseData.setError("用户不存在");
            return responseData;
        }
        if(user.getUserName() != null && !user.getUserName().equals(oldUser.getUserName())) {
            responseData.setError("不能修改用户名！");
            return responseData;
        }
        if(user.getEmail() != null && !user.getEmail().equals(oldUser.getEmail())) {
            responseData.setError("不能修改邮箱！");
            return responseData;
        }
        if (user.getPassword() != null) {
            String hashPassword = HashUtils.hashEncrypt(user.getPassword(),CommonConstant.PASSWORD_HASH);
            user.setPassword(hashPassword);
        }

        Integer result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 1) {
            responseData.setOK("修改成功");
        } else {
            responseData.setError("修改失败");
        }
        return responseData;
    }

    @Override
    public ResponseData<String> registerUser(User user) {
        ResponseData<String> responseData = new ResponseData<>();

        User checkUserEmail = userMapper.loginByEmail(user.getEmail());

        if(checkUserEmail != null) {
            responseData.setError("该用户邮箱已存在！");
            return responseData;
        }
        user.setIsDelete(CommonConstant.DELETED_NO);
        user.setStatus(CommonConstant.USER_STATUS_UNAUDITED);
        user.setJobLevel(CommonConstant.JOB_LEVEL_STUDENT);
        user.setPassword(HashUtils.hashEncrypt(user.getPassword(),CommonConstant.PASSWORD_HASH));
        Integer result = userMapper.insert(user);
        if (result == 0) {
            responseData.setError("注册失败");
            return responseData;
        }
        responseData.setOK("注册成功");
        return responseData;
    }

    @Override
    public ResponseData<String> changeUserInfoByMyself(RegisterUserBO registerUserBO) {
        return null;
    }

    @Override
    public ResponseData<UserLoginBO> login(String email, String password) {
        ResponseData<UserLoginBO> responseData = new ResponseData<>();
        UserLoginBO userLoginBO = new UserLoginBO();
        String hashPassword = HashUtils.hashEncrypt(password, CommonConstant.PASSWORD_HASH);
        User user = userMapper.loginByEmail(email);
        if(user == null ) {
            responseData.setError("用户不存在");
            return responseData;
        }
        userLoginBO.setCode(user.getJobLevel().toString());
        if(user.getStatus().equals(CommonConstant.USER_STATUS_ABNORMAL)) {
            responseData.setError("账号冻结 请联系管理员");
            return responseData;
        }
        if(user.getStatus().equals(CommonConstant.USER_STATUS_UNAUDITED)) {
            responseData.setError("账号未审核 请联系管理员审核");
            return responseData;
        }
        if(hashPassword.equals(user.getPassword())) {
            userLoginBO.setToken(JwtUtils.geneJsonWebToken(user));
        } else {
            responseData.setError("密码错误");
            return responseData;
        }
        responseData.setOK("登陆成功",userLoginBO);
        return responseData;
    }

    @Override
    public ResponseData<String> password(PasswordBO passwordBO) {
        ResponseData<String> responseData = new ResponseData<>();
        if (emailService.checkPasswordEmailCode(passwordBO.getEmail(),passwordBO.getCode())) {
            User user = userMapper.loginByEmail(passwordBO.getEmail());
            if(user == null) {
                responseData.setError("没有该用户");
                return responseData;
            }
            String hashPassword = HashUtils.hashEncrypt(passwordBO.getPassword(), CommonConstant.PASSWORD_HASH);

            user.setPassword(hashPassword);
            userMapper.updateByPrimaryKeySelective(user);
            responseData.setOK("更改成功");
        } else {
            responseData.setError("验证码错误");
        }
        return responseData;
    }

    @Override
    public ResponseData<String> agreeUser(Integer id) {
        ResponseData<String> responseData = new ResponseData<>();
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            responseData.setError("用户不存在");
            return responseData;
        }
        user.setStatus(CommonConstant.USER_STATUS_NORMAL);

        Integer result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 1) {
            responseData.setOK("修改成功");
        } else {
            responseData.setError("修改失败");
        }
        return responseData;
    }

    @Override
    public ResponseData<String> disagreeUser(Integer id) {
        ResponseData<String> responseData = new ResponseData<>();
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            responseData.setError("用户不存在");
            return responseData;
        }
        user.setStatus(CommonConstant.USER_STATUS_ABNORMAL);

        Integer result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 1) {
            responseData.setOK("修改成功");
        } else {
            responseData.setError("修改失败");
        }
        return responseData;
    }
}
