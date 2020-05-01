package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.base.constants.Response;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetTypeMapper;
import com.assetManage.tusdt.model.AssetType;
import com.assetManage.tusdt.model.AssetTypeExample;
import com.assetManage.tusdt.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 21:05
 */
@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeMapper assetTypeMapper;

    @Override
    public ResponseData<String> addAssetType(String assetName) {
        ResponseData<String> response = new ResponseData<>();
        AssetType assetType = new AssetType();
        assetType.setTypeName(assetName);
        assetType.setCreateTime(new Date());
        assetType.setIsDelete(CommonConstant.DELETED_NO);
        Integer result = assetTypeMapper.insert(assetType);
        if(result > 0) {
            response.setOK("添加成功");
        } else {
            response.setError("添加失败");
        }
        return response;
    }

    @Override
    public List<AssetType> getAssetTypeList() {
        AssetTypeExample example = new AssetTypeExample();
        AssetTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(CommonConstant.DELETED_NO);
        List<AssetType> assetTypeList = assetTypeMapper.selectByExample(example);
        return assetTypeList;
    }

    @Override
    public ResponseData<String> removeAssetType(Integer id) {
        ResponseData<String> response = new ResponseData<>();
        AssetType assetType = assetTypeMapper.selectByPrimaryKey(id);
        if(assetType == null) {
            response.setError("未查找到该标签");
        } else {
            assetType.setIsDelete(CommonConstant.DELETED_YES);
            assetTypeMapper.updateByPrimaryKey(assetType);
            response.setOK("删除成功");
        }
        return response;
    }
}
