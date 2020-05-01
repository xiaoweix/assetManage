package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.AssetType;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 16:22
 */
public interface AssetTypeService {

    ResponseData<String> addAssetType(String assetName);

    List<AssetType> getAssetTypeList();

    ResponseData<String> removeAssetType(Integer id);
}
