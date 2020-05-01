package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.AssetType;
import com.assetManage.tusdt.model.AssetTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetTypeMapper {
    int countByExample(AssetTypeExample example);

    int deleteByExample(AssetTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetType record);

    int insertSelective(AssetType record);

    List<AssetType> selectByExample(AssetTypeExample example);

    AssetType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetType record, @Param("example") AssetTypeExample example);

    int updateByExample(@Param("record") AssetType record, @Param("example") AssetTypeExample example);

    int updateByPrimaryKeySelective(AssetType record);

    int updateByPrimaryKey(AssetType record);
}