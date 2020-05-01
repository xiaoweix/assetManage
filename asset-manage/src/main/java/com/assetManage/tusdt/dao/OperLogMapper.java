package com.assetManage.tusdt.dao;

import com.assetManage.tusdt.model.OperLog;
import com.assetManage.tusdt.model.OperLogExample;
import java.util.List;

import com.assetManage.tusdt.model.bo.OperLogListBO;
import org.apache.ibatis.annotations.Param;

public interface OperLogMapper {
    int countByExample(OperLogExample example);

    int deleteByExample(OperLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperLog record);

    int insertSelective(OperLog record);

    List<OperLog> selectByExample(OperLogExample example);

    OperLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperLog record, @Param("example") OperLogExample example);

    int updateByExample(@Param("record") OperLog record, @Param("example") OperLogExample example);

    int updateByPrimaryKeySelective(OperLog record);

    int updateByPrimaryKey(OperLog record);

    List<OperLogListBO> getOperLogList(@Param("userName") String userName,@Param("dataFrom") String dataFrom,@Param("dataEnd") String dataEnd);
}