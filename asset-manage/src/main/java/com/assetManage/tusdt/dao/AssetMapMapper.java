package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.AssetMap;
import com.assetManage.tusdt.model.AssetMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetMapMapper {
    int countByExample(AssetMapExample example);

    int deleteByExample(AssetMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetMap record);

    int insertSelective(AssetMap record);

    List<AssetMap> selectByExample(AssetMapExample example);

    AssetMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetMap record, @Param("example") AssetMapExample example);

    int updateByExample(@Param("record") AssetMap record, @Param("example") AssetMapExample example);

    int updateByPrimaryKeySelective(AssetMap record);

    int updateByPrimaryKey(AssetMap record);
}