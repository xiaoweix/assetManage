package com.assetManage.tusdt.model.bo;


import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:08
 */
public class IndexDataBO {

    private Integer assetSum;

    private Integer assetInUse;

    private Integer lendAssetNum;

    private Integer assetDamageNum;

    private Integer assetUseLend;

    private Integer assetUseReceive;

    private Integer assetUseConstant;

    private Integer assetUserRepair;

    private List<AssetLendRankBO> assetLendRankList;

    private List<HashMap<String,Integer>> assetUseHalfYearBOList;

    public Integer getAssetSum() {
        return assetSum;
    }

    public void setAssetSum(Integer assetSum) {
        this.assetSum = assetSum;
    }

    public Integer getAssetInUse() {
        return assetInUse;
    }

    public void setAssetInUse(Integer assetInUse) {
        this.assetInUse = assetInUse;
    }

    public Integer getLendAssetNum() {
        return lendAssetNum;
    }

    public void setLendAssetNum(Integer lendAssetNum) {
        this.lendAssetNum = lendAssetNum;
    }

    public Integer getAssetDamageNum() {
        return assetDamageNum;
    }

    public void setAssetDamageNum(Integer assetDamageNum) {
        this.assetDamageNum = assetDamageNum;
    }

    public Integer getAssetUseLend() {
        return assetUseLend;
    }

    public void setAssetUseLend(Integer assetUseLend) {
        this.assetUseLend = assetUseLend;
    }

    public Integer getAssetUseReceive() {
        return assetUseReceive;
    }

    public void setAssetUseReceive(Integer assetUseReceive) {
        this.assetUseReceive = assetUseReceive;
    }

    public Integer getAssetUseConstant() {
        return assetUseConstant;
    }

    public void setAssetUseConstant(Integer assetUseConstant) {
        this.assetUseConstant = assetUseConstant;
    }

    public Integer getAssetUserRepair() {
        return assetUserRepair;
    }

    public void setAssetUserRepair(Integer assetUserRepair) {
        this.assetUserRepair = assetUserRepair;
    }

    public List<AssetLendRankBO> getAssetLendRankList() {
        return assetLendRankList;
    }

    public void setAssetLendRankList(List<AssetLendRankBO> assetLendRankList) {
        this.assetLendRankList = assetLendRankList;
    }

    public List<HashMap<String, Integer>> getAssetUseHalfYearBOList() {
        return assetUseHalfYearBOList;
    }

    public void setAssetUseHalfYearBOList(List<HashMap<String, Integer>> assetUseHalfYearBOList) {
        this.assetUseHalfYearBOList = assetUseHalfYearBOList;
    }
}
