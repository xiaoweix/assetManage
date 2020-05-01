package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.Warehouse;
import com.assetManage.tusdt.model.WarehouseExample;
import java.util.List;

import com.assetManage.tusdt.model.bo.WarehouseBO;
import org.apache.ibatis.annotations.Param;

public interface WarehouseMapper {
    int countByExample(WarehouseExample example);

    int deleteByExample(WarehouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    List<Warehouse> selectByExample(WarehouseExample example);

    Warehouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Warehouse record, @Param("example") WarehouseExample example);

    int updateByExample(@Param("record") Warehouse record, @Param("example") WarehouseExample example);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);

    List<WarehouseBO> warehouseList(@Param("warehouseId") Integer warehouseId, @Param("warehouseName") String warehouseName, @Param("address") String address);

}