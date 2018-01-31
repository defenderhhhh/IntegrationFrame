package com.martin.integrationframe.mvp.view;

import com.martin.integrationframe.base.mvp.ui.BaseMvpView;

/**
 * 作者：Martin on 2018/1/31 13:30
 * 邮箱：martin0207mfh@163.com
 */
public interface SplashView extends BaseMvpView {

    /**
     * 计时回调
     *
     * @param time 当前计时时间
     */
    public void countDown(long time);

}
