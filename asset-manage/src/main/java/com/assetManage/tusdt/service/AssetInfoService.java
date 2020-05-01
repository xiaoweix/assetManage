package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.AssetUseHistoryBO;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-15
 * Time: 23:55
 */
public interface AssetInfoService {

    /**
     * 资产入库
     * @param assetInfo
     * @return
     */
    ResponseData<String> addAsset(Integer userId,AssetInfo assetInfo);

    /**
     * 资产列表
     * @param currPage
     * @param pageSize
     * @param assetId
     * @param assetName
     * @param repositoryName
     * @param status
     * @return
     */
    List<AssetListBO> getAssetList(Integer currPage, Integer pageSize, Integer assetId, String assetName, String repositoryName, Integer status, Integer useType);

    /**
     * 资产使用历史
     * @param currPage
     * @param pageSize
     * @param assetId
     * @param assetName
     * @param userName
     * @param type
     * @return
     */
    List<AssetUseHistoryBO> getAssetHistory(Integer currPage, Integer pageSize, Integer assetId, String assetName, String userName, Integer type);

    ResponseData<AssetInfo> getAssetInfoDetail(Integer id);

    /**
     * 资产入库
     * @param assetInfo
     * @return
     */
    ResponseData<String> modifyAsset(Integer userId,AssetInfo assetInfo);
}
