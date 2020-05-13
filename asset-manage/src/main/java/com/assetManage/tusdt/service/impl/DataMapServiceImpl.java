package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetMapMapper;
import com.assetManage.tusdt.model.AssetMap;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.MapListBO;
import com.assetManage.tusdt.service.DataMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataMapServiceImpl implements DataMapService {

    @Autowired
    private AssetMapMapper assetMapMapper;

    @Override
    public ResponseData<String> addMap(AssetMap assetMap) {
        ResponseData<String> responseData = new ResponseData<>();
        assetMap.setIsDelete(CommonConstant.DELETED_NO);
        assetMap.setCreateTime(new Date());
        assetMapMapper.insert(assetMap);
        responseData.setOK("插入成功");
        return responseData;
    }

    @Override
    public List<MapListBO> mapList() {
        return assetMapMapper.mapList();
    }

    @Override
    public List<AssetMap> getAllMap() {

        return assetMapMapper.allMapList();
    }

    @Override
    public List<AssetListBO> getByMapId(Integer mapId) {
        return assetMapMapper.getMapAsset(mapId);
    }

    @Override
    public List<Integer> getAssetLocation(Integer assetId, String assetName) {
        return assetMapMapper.getAssetLocation(assetId,assetName);
    }
}
