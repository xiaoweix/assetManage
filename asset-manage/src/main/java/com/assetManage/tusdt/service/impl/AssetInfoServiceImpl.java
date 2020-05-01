package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetApplyMapper;
import com.assetManage.tusdt.dao.AssetInfoMapper;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.AssetUseHistoryBO;
import com.assetManage.tusdt.service.AssetInfoService;
import com.assetManage.tusdt.service.AssetLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-15
 * Time: 23:56
 */
@Service
public class AssetInfoServiceImpl implements AssetInfoService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Autowired
    private AssetApplyMapper assetApplyMapper;

    @Resource
    private AssetLogInfoService assetLogInfoService;



    @Override
    public ResponseData<String> addAsset(Integer userId, AssetInfo assetInfo) {
        ResponseData<String> responseData = new ResponseData<>();
        assetInfo.setIsDelete(CommonConstant.DELETED_NO);

        Integer result = assetInfoMapper.insert(assetInfo);
        if (result == 1) {
            responseData.setOK("插入成功");
            assetLogInfoService.addOperLog(userId,String.format("新增资源 '%s' ",assetInfo.getAssetName()));
        } else {
            responseData.setError("插入失败");
        }
        return responseData;
    }

    @Override
    public List<AssetListBO> getAssetList(Integer currPage, Integer pageSize, Integer assetId, String assetName, String repositoryName, Integer status, Integer useType) {
//        PageMethod.startPage(currPage, pageSize);
        List<AssetListBO> assetListBOList = assetInfoMapper.selectAssetList(assetId, assetName, repositoryName, status, useType);
        return assetListBOList;
    }

    @Override
    public List<AssetUseHistoryBO> getAssetHistory(Integer currPage, Integer pageSize, Integer assetId, String assetName, String userName, Integer type) {

        return assetApplyMapper.getAssetUseHistory(assetId, assetName, userName, type,null);
    }

    @Override
    public ResponseData<AssetInfo> getAssetInfoDetail(Integer id) {
        ResponseData<AssetInfo> responseData = new ResponseData<>();
        AssetInfo assetInfo = assetInfoMapper.selectByPrimaryKey(id);
        if(assetInfo != null) {
            responseData.setOK("获取成功", assetInfo);
        } else {
            responseData.setError("获取失败");
        }
        return responseData;
    }

    @Override
    public ResponseData<String> modifyAsset(Integer userId, AssetInfo assetInfo) {
        ResponseData<String> responseData = new ResponseData<>();
        Integer result = assetInfoMapper.updateByPrimaryKeySelective(assetInfo);
        if (result == 1) {
            responseData.setOK("更新成功");
            assetLogInfoService.addOperLog(userId,String.format("修改资源 '%s' ",assetInfo.getAssetName()));
        } else {
            responseData.setError("更新失败");
        }
        return responseData;
    }


}
