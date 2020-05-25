package com.assetManage.tusdt.constants;

/**
 * Description:
 * Author: xxw
 * Date: 2020-03-16
 * Time: 21:14
 */
public class CommonConstant {
    /**
     * 已删除
     */
    public static final Integer DELETED_YES = 1;

    /**
     * 未删除
     */
    public static final Integer DELETED_NO = 0;

    /**
     * 加密算法的名字
     */
    public static final String PASSWORD_HASH = "SHA-256";
    /**
     * 用户状态 0:未审核 Unaudited 1 冻结  2 正常
     */
    public static final Integer USER_STATUS_UNAUDITED = 0;

    public static final Integer USER_STATUS_ABNORMAL = 1;

    public static final Integer USER_STATUS_NORMAL = 2;

    /**
     * 职位等级
     * 用户等级1:普通学生用户,2:教师用户,3:管理员，9:超级管理员
     */

    public static final Integer JOB_LEVEL_STUDENT = 1;

    public static final Integer JOB_LEVEL_TEACHER = 2;

    public static final Integer JOB_LEVEL_ADMIN = 3;

    public static final Integer JOB_LEVEL_SUPER_ADMIN = 9;

    /**
     * 是否能借用 0,借用 1、领用 2、使用 '
     */
    public static final Integer ASSET_USE_TYPE_LEND = 1;

    public static final Integer ASSET_USE_TYPE_GET = 2;

    public static final Integer ASSET_USE_TYPE_USE = 3;
    /**
     * 类型1,借用 2、领用 3、使用 ' 4采购  5反馈  6归还
     */
    public static final Integer ASSET_APPLY_LEND = 1;

    public static final Integer ASSET_APPLY_GET = 2;

    public static final Integer ASSET_APPLY_USE = 3;

    public static final Integer ASSET_APPLY_BUY = 4;

    public static final Integer ASSET_APPLY_FEEDBACK = 5;

    public static final Integer ASSET_APPLY_RETURN = 6;

    /**
     * 处理结果 0待处理,1同意,2拒绝  3归还
     */
    public static final Integer ASSET_APPLY_RESULT_UNAUDITED = 0;

    public static final Integer ASSET_APPLY_RESULT_AGREE = 1;

    public static final Integer ASSET_APPLY_RESULT_DISAGREE = 2;

    public static final Integer ASSET_APPLY_RESULT_COMPLETE = 3;

    /**
     * 资源状态
     * 资源状态,0:空闲 1:借用中 2:使用中 3:维修中 4:报废
     */
    public static final Integer ASSET_INFO_STATUS_IDLE = 0;

    public static final Integer ASSET_INFO_STATUS_LEND = 1;

    public static final Integer ASSET_INFO_STATUS_USING = 2;

    public static final Integer ASSET_INFO_STATUS_REPAIR = 3;

    public static final Integer ASSET_INFO_STATUS_BREAK = 4;


}
