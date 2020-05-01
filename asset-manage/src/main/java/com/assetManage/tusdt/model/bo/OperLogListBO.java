package com.assetManage.tusdt.model.bo;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:03
 */
public class OperLogListBO {

    private Integer id;

    private Integer userId;

    private String userName;

    private String remarks;

    private Date startTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
