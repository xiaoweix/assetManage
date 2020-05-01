package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetListBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetInfo record);

    int insertSelective(AssetInfo record);

    AssetInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetInfo record);

    int updateByPrimaryKey(AssetInfo record);

    int countAsset();

    int countAssetInUse();

    int countAssetLend();

    int countAssetDamageNum();

    List<AssetListBO> selectAssetList(@Param("assetId") Integer assetId, @Param("assetName") String assetName, @Param("repositoryName") String repositoryName, @Param("status") Integer status, @Param("useType") Integer useType);
}