package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.dao.AssetMapMapper;
import com.assetManage.tusdt.model.AssetMap;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.MapListBO;
import com.assetManage.tusdt.service.DataMapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataMapServiceImpl implements DataMapService {

    private AssetMapMapper assetMapMapper;

    @Override
    public ResponseData<String> addMap(AssetMap assetMap) {
        ResponseData<String> responseData = new ResponseData<>();
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
}
