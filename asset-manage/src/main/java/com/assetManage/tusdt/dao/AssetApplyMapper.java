package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.AssetApplyExample;

import java.util.HashMap;
import java.util.List;

import com.assetManage.tusdt.model.bo.AssetLendRankBO;
import com.assetManage.tusdt.model.bo.AssetLogInfoDetailBO;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.model.bo.AssetUseHistoryBO;
import org.apache.ibatis.annotations.Param;

public interface AssetApplyMapper {
    int countByExample(AssetApplyExample example);

    int deleteByExample(AssetApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetApply record);

    int insertSelective(AssetApply record);

    List<AssetApply> selectByExample(AssetApplyExample example);

    AssetApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetApply record, @Param("example") AssetApplyExample example);

    int updateByExample(@Param("record") AssetApply record, @Param("example") AssetApplyExample example);

    int updateByPrimaryKeySelective(AssetApply record);

    int updateByPrimaryKey(AssetApply record);

    AssetLogInfoDetailBO selectApplyDetail(@Param("id") Integer id);

    List<AssetApplyListBO> getAssetApplyList(@Param("assetId") Integer assetId, @Param("userName") String userName, @Param("status") Integer status, @Param("telephone") String telephone, @Param("jobLevel") Integer jobLevel);

    List<AssetUseHistoryBO> getAssetUseHistory(@Param("assetId") Integer assetId, @Param("assetName") String assetName, @Param("userName") String userName, @Param("type") Integer type,@Param("userId") Integer userId);

    int countLend();

    int countRepair();

    int countUse();

    int countGet();

    List<AssetLendRankBO> getRank();

    List<HashMap<String,Integer>> selectLatelyTask(@Param("start") String start , @Param("end")String end);
}