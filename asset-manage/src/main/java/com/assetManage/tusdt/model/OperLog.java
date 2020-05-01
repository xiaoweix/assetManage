package com.assetManage.tusdt.model;

import java.util.Date;

public class OperLog {
    private Integer id;

    private Integer userId;

    private String remarks;

    private Date startTime;

    public OperLog(Integer userId, String remarks, Date startTime) {
        this.userId = userId;
        this.remarks = remarks;
        this.startTime = startTime;
    }

    public OperLog() {
    }

    public OperLog(Integer id, Integer userId, String remarks, Date startTime) {
        this.id = id;
        this.userId = userId;
        this.remarks = remarks;
        this.startTime = startTime;
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


}