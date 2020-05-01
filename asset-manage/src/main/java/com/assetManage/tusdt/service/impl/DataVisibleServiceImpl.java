package com.assetManage.tusdt.service.impl;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.dao.AssetApplyMapper;
import com.assetManage.tusdt.dao.AssetInfoMapper;
import com.assetManage.tusdt.model.bo.IndexDataBO;
import com.assetManage.tusdt.service.DataVisibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 15:16
 */
@Service
public class DataVisibleServiceImpl implements DataVisibleService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Autowired
    private AssetApplyMapper assetApplyMapper;

    @Override
    public ResponseData<IndexDataBO> getIndexData() {

        ResponseData<IndexDataBO> responseData = new ResponseData<>();
        IndexDataBO indexDataBO = new IndexDataBO();

        indexDataBO.setAssetSum(assetInfoMapper.countAsset());
        indexDataBO.setAssetInUse(assetInfoMapper.countAssetInUse());
        indexDataBO.setLendAssetNum(assetInfoMapper.countAssetLend());
        indexDataBO.setAssetDamageNum(assetInfoMapper.countAssetDamageNum());

        indexDataBO.setAssetUserRepair(assetApplyMapper.countRepair());
        indexDataBO.setAssetUseLend(assetApplyMapper.countLend());
        indexDataBO.setAssetUseReceive(assetApplyMapper.countGet());
        indexDataBO.setAssetUseConstant(assetApplyMapper.countUse());

        indexDataBO.setAssetLendRankList(assetApplyMapper.getRank());

        //计算统计的这半年的开始时间和结束时间 即start - end 这半年的数据
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String gtimelast = sdf.format(c.getTime()); //上月
        int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay);
        String end = sdf.format(c.getTime()); //上月最后一天

        c.add(Calendar.MONTH, -5);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        String start = sdf2.format(c.getTime()); //半年前的第一天

//        indexDataBO.setAssetUseHalfYearBOList(assetApplyMapper.selectLatelyTask(start,end));
//

        responseData.setOK("获取成功",indexDataBO);
        return responseData;
    }
}
