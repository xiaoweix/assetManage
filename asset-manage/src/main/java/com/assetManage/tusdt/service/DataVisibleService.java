package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;
import com.assetManage.tusdt.model.bo.IndexDataBO;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-02
 * Time: 01:25
 */
public interface DataVisibleService {

    ResponseData<IndexDataBO> getIndexData();

}
