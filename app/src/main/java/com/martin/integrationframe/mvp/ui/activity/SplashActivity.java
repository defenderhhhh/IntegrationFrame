package com.martin.integrationframe.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.mvp.ui.activity.mvp.presenter.SplashP;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.SplashV;

/**
 * 作者：Martin on 2018/1/31 13:25
 * 邮箱：martin0207mfh@163.com
 *
 * @description 解决闪屏问题
 */
@CreatePresenter(SplashP.class)
public class SplashActivity extends BaseMvpActivity<SplashV, SplashP> implements SplashV {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().countdown();
    }

    @Override
    public void countDownFinish() {
        MainActivity.start(getContext());
        finish();
    }

}
