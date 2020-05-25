package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.constants.CommonConstant;
import com.assetManage.tusdt.dao.AssetApplyMapper;
import com.assetManage.tusdt.dao.AssetInfoMapper;
import com.assetManage.tusdt.model.AssetApply;
import com.assetManage.tusdt.model.AssetInfo;
import com.assetManage.tusdt.model.bo.AssetApplyBO;
import com.assetManage.tusdt.model.bo.AssetLogInfoDetailBO;
import com.assetManage.tusdt.model.bo.AssetApplyListBO;
import com.assetManage.tusdt.service.AssetApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (assetInfo.getStatus().equals(CommonConstant.ASSET_INFO_STATUS_LEND)) {
            responseData.setError("资产借用中，不可同意借用申请");
            return responseData;
        }
        assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_AGREE);
        assetApplyMapper.updateByPrimaryKeySelective(assetApply);
        if(assetApply.getType().equals(CommonConstant.ASSET_APPLY_LEND) && !assetInfo.getStatus().equals(CommonConstant.ASSET_INFO_STATUS_REPAIR)) {
            assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_LEND);
        } else if (assetApply.getType().equals(CommonConstant.ASSET_APPLY_GET)) {
            assetInfo.setAssetNum(assetInfo.getAssetNum() - assetApply.getNumber());
        } else if (assetApply.getType().equals(CommonConstant.ASSET_APPLY_FEEDBACK)) {
            assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_REPAIR);
        } else if (assetApply.getType().equals(CommonConstant.ASSET_APPLY_BUY)) {

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
        assetApplyMapper.updateByPrimaryKeySelective(assetApply);
        responseData.setOK("操作成功");
        return responseData;
    }

    @Override
    public ResponseData<String> postApply(AssetApplyBO assetApply) {
        ResponseData<String> responseData = new ResponseData<>();
        AssetInfo assetInfo = assetInfoMapper.selectByPrimaryKey(assetApply.getAssetId());
        if (!assetInfo.getStatus().equals(CommonConstant.ASSET_INFO_STATUS_IDLE)) {
            responseData.setError("资产使用或维修中，无法申请");
            return responseData;
        }
        if (assetApply.getType().equals(CommonConstant.ASSET_USE_TYPE_GET)) {
            assetApply.setStartTime(new Date());
        }
        if (assetApply.getType().equals(CommonConstant.ASSET_USE_TYPE_USE)) {

            assetInfo.setMapId(assetApply.getMapId());
            assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_USING);
            assetInfoMapper.updateByPrimaryKeySelective(assetInfo);
            assetApply.setStartTime(new Date());
            assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_COMPLETE);
        } else {
            assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_UNAUDITED);
        }
        assetApplyMapper.insert(assetApply);
        responseData.setOK("提交成功");
        return responseData;
    }

    @Override
    public ResponseData<String> returnAsset(Integer applyId) {
        ResponseData<String> responseData = new ResponseData<>();
        AssetApply assetApply = assetApplyMapper.selectByPrimaryKey(applyId);

        AssetInfo assetInfo = assetInfoMapper.selectByPrimaryKey(assetApply.getAssetId());

        assetApply.setResult(CommonConstant.ASSET_APPLY_RESULT_COMPLETE);
        assetInfo.setStatus(CommonConstant.ASSET_INFO_STATUS_IDLE);

        assetApplyMapper.updateByPrimaryKeySelective(assetApply);
        assetInfoMapper.updateByPrimaryKeySelective(assetInfo);
        responseData.setOK("操作成功");
        return responseData;
    }
}
