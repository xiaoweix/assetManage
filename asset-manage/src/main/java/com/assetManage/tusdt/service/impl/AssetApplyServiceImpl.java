package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetApplyMapper;
import com.assetManage.tusdt.dao.AssetInfoMapper;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetLogInfoDetailBO;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.service.AssetApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 23:04
 */
@Service
public class AssetApplyServiceImpl implements AssetApplyService {

    @Autowired
    private AssetApplyMapper assetApplyMapper;

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Override
    public List<AssetApplyListBO> getAssetApplyList(Integer currPage, Integer pageSize, Integer assetId, String userName, Integer status, String telephone, Integer jobLevel) {

        return assetApplyMapper.getAssetApplyList(assetId, userName, status, telephone, jobLevel);
    }

    @Override
    public AssetLogInfoDetailBO getApplyInfoDetail(Integer applyId) {
        return assetApplyMapper.selectApplyDetail(applyId);
    }

    @Override
    public ResponseData<String> agreeApply(Integer applyId) {
        ResponseData<String> responseData = new ResponseData<>();
        AssetApply assetApply = assetApplyMapper.selectByPrimaryKey(applyId);
        AssetInfo assetInfo = assetInfoMapper.selectByPrimaryKey(assetApply.getAssetId());

        assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_AGREE);
        assetApplyMapper.updateByPrimaryKeySelective(assetApply);
        if(assetApply.getType().equals(CommonConstant.ASSET_APPLY_LEND) && !assetInfo.getStatus().equals(CommonConstant.ASSET_INFO_STATUS_REPAIR)) {
            assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_LEND);
        } else if (assetApply.getType().equals(CommonConstant.ASSET_APPLY_GET)) {
            assetInfo.setAssetNum(assetInfo.getAssetNum() - assetApply.getNumber());
        } else if (assetApply.getType().equals(CommonConstant.ASSET_APPLY_FEEDBACK)) {
            assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_REPAIR);
        }
        assetInfoMapper.updateByPrimaryKeySelective(assetInfo);
        responseData.setOK("操作成功");
        return responseData;
    }

    @Override
    public ResponseData<String> refuseApply(Integer applyId) {
        ResponseData<String> responseData = new ResponseData<>();
        AssetApply assetApply = assetApplyMapper.selectByPrimaryKey(applyId);
        assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_DISAGREE);
        responseData.setOK("操作成功");
        return responseData;
    }

    @Override
    public ResponseData<String> postApply(AssetApply assetApply) {
        ResponseData<String> responseData = new ResponseData<>();
        assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_UNAUDITED);
        assetApplyMapper.insert(assetApply);
        responseData.setOK("提交成功");
        return responseData;
    }
}
