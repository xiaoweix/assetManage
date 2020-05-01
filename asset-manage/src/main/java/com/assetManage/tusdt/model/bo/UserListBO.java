package com.assetManage.tusdt.model.bo;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 16:46
 */
public class UserListBO {

    private Integer id;

    private String userName;

    private String telephone;

    private Integer jobLevel;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
