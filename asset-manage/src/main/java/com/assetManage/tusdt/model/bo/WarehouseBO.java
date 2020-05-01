package com.assetManage.tusdt.model.bo;

import java.util.Date;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-23
 * Time: 13:46
 */
public class WarehouseBO {

    private Integer id;

    private String name;

    private String address;

    private String manageName;

    private Date createTime;

    private Integer assetNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(Integer assetNum) {
        this.assetNum = assetNum;
    }
}
