package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.dao.OperLogMapper;
import com.assetManage.tusdt.model.OperLog;
import com.assetManage.tusdt.model.bo.OperLogListBO;
import com.assetManage.tusdt.service.AssetLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 15:14
 */
@Service
public class AssetLogInfoServiceImpl implements AssetLogInfoService {

    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public List<OperLogListBO> getOperLog(String userName, String dataFrom, String dataEnd) {
        return operLogMapper.getOperLogList(userName,dataFrom,dataEnd);
    }

    @Override
    public void addOperLog(Integer userId, String remark) {
        OperLog operLog = new OperLog(userId, remark, new Date());
        operLogMapper.insert(operLog);
    }


}
