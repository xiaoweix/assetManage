package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.AssetMap;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.MapListBO;

import java.util.List;

public interface DataMapService {

    ResponseData<String> addMap(AssetMap assetMap);

    List<MapListBO> mapList();

    List<AssetMap> getAllMap();

    List<AssetListBO> getByMapId(Integer mapId);

    List<Integer> getAssetLocation(Integer assetId, String assetName);
}
