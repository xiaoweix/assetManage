package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.WarehouseMapper;
import com.assetManage.tusdt.model.Warehouse;
import com.assetManage.tusdt.model.bo.WarehouseBO;
import com.assetManage.tusdt.service.AssetLogInfoService;
import com.assetManage.tusdt.service.WarehouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-23
 * Time: 13:49
 */
@Service
public class WarehouseInfoServiceImpl implements WarehouseInfoService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Resource
    private AssetLogInfoService assetLogInfoService;

    @Override
    public ResponseData<String> addWarehouse(Integer userId, Warehouse warehouse) {
        ResponseData<String> responseData = new ResponseData<>();
        warehouse.setCreateTime(new Date());
        warehouse.setIsDelete(CommonConstant.DELETED_NO);
        Integer id = warehouseMapper.insert(warehouse);
        responseData.setOK("添加成功");
        assetLogInfoService.addOperLog(userId,String.format("添加仓库 '%s' ",warehouse.getName()));
        return responseData;
    }

    @Override
    public ResponseData<String> modifyWarehouse(Integer userId, Warehouse warehouse) {
        ResponseData<String> responseData = new ResponseData<>();
        assetLogInfoService.addOperLog(userId,String.format("修改仓库 '%s' ",warehouse.getName()));
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
        return responseData;
    }

    @Override
    public ResponseData<String> removeWarehouse(Integer userId, Integer id) {
        ResponseData<String> responseData = new ResponseData<>();
        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(id);
        warehouse.setIsDelete(CommonConstant.DELETED_YES);
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
        assetLogInfoService.addOperLog(userId,String.format("删除仓库 '%s' ",warehouse.getName()));
        return responseData;
    }

    @Override
    public List<WarehouseBO> getWarehouseList(Integer currPage, Integer pageSize, Integer warehouseId, String warehouseName, String address) {
        List<WarehouseBO> warehouseBOList = warehouseMapper.warehouseList(warehouseId, warehouseName, address);
        return warehouseBOList;
    }
}
