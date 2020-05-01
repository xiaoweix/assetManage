package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.bo.AssetLogInfoDetailBO;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 23:04
 */
public interface AssetApplyService {

    /**
     * 获取申请资源的列表
     * @param currPage
     * @param pageSize
     * @param assetId
     * @param userName
     * @param status
     * @param telephone
     * @param jobLevel
     * @return
     */
    List<AssetApplyListBO> getAssetApplyList(Integer currPage, Integer pageSize, Integer assetId, String userName, Integer status, String telephone, Integer jobLevel);

    /**
     * 获取申请信息详情
     * @param applyId
     * @return
     */
    AssetLogInfoDetailBO getApplyInfoDetail(Integer applyId);

    /**
     * 同意申请
     * @param applyId
     * @return
     */
    ResponseData<String> agreeApply(Integer applyId);

    /**
     * 拒绝申请
     * @param applyId
     * @return
     */
    ResponseData<String> refuseApply(Integer applyId);

    ResponseData<String> postApply(AssetApply assetApply);
}
