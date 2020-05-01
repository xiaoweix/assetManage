package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.Pagination;
import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.Warehouse;
import com.assetManage.tusdt.model.bo.AssetListBO;
import com.assetManage.tusdt.model.bo.WarehouseBO;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-23
 * Time: 13:39
 */
public interface WarehouseInfoService {

    ResponseData<String> addWarehouse(Integer userId, Warehouse warehouse);

    ResponseData<String> modifyWarehouse(Integer userId, Warehouse warehouse);

    ResponseData<String> removeWarehouse(Integer userId,Integer id);

    List<WarehouseBO> getWarehouseList(Integer currPage, Integer pageSize, Integer warehouseId, String warehouseName, String address);
}
