package com.assetManage.tusdt.service;

import com.assetManage.tusdt.base.common.ResponseData;

/**
 * Description:
 * Author: xxw
 * Date: 2020-04-22
 * Time: 14:51
 */
public interface EmailService {

    ResponseData<String> sendEmail(String email);

    ResponseData<String> checkEmailCode(String email, Integer emailCode);
}
