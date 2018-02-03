package com.martin.integrationframe.constant;

import android.app.Activity;

/**
 * 作者：Martin on 2018/1/31 12:51
 * 邮箱：martin0207mfh@163.com
 *
 * @description 经常使用的Code值
 */
public interface ConstantCode {

    // =================================   返回码   =======================================

    //成功，返回了内容
    int RESULT_OK = Activity.RESULT_OK;

    //取消，没有返回值内容
    int RESULT_CANCELED = Activity.RESULT_CANCELED;

    //启动用户定义的活动结果。
    int RESULT_FIRST_USER = Activity.RESULT_FIRST_USER;

    // =================================  请求码  ===========================================

    //选择照片
    int REQUEST_PHOTO_PICK = 100;

    //拍摄照片
    int REQUEST_PHOTO_TAKE = 101;

}
