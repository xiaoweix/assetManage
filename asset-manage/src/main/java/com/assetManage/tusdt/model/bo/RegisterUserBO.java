package com.assetManage.tusdt.model.bo;

import com.assetManage.tusdt.model.User;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 16:55
 */
public class RegisterUserBO extends User {

    private Integer checkCode;

    private String passwordAgain;

    public Integer getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(Integer checkCode) {
        this.checkCode = checkCode;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }
}
