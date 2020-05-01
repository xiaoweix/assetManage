package com.assetManage.tusdt.model.bo;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 16:39
 */
public class UserDetailBO {

    private Integer id;

    private String userName;

    private String signature;

    private String telephone;

    private String email;

    private List<AssetUseHistoryBO> assetUseHistoryBOList;

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AssetUseHistoryBO> getAssetUseHistoryBOList() {
        return assetUseHistoryBOList;
    }

    public void setAssetUseHistoryBOList(List<AssetUseHistoryBO> assetUseHistoryBOList) {
        this.assetUseHistoryBOList = assetUseHistoryBOList;
    }
}
