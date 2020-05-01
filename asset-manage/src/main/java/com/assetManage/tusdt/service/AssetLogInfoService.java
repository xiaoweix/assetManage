package com.assetManage.tusdt.service;

import com.assetManage.tusdt.model.bo.OperLogListBO;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 00:26
 */
public interface AssetLogInfoService {

    List<OperLogListBO> getOperLog(String userName, String dataFrom, String dataEnd);

    void addOperLog(Integer userId, String remark);


}
