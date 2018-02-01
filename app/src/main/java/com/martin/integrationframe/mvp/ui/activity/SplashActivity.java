package com.martin.integrationframe.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.mvp.presenter.SplashPresenter;
import com.martin.integrationframe.mvp.view.SplashView;
import com.umeng.analytics.MobclickAgent;

/**
 * 作者：Martin on 2018/1/31 13:25
 * 邮箱：martin0207mfh@163.com
 */
@CreatePresenter(SplashPresenter.class)
public class SplashActivity extends BaseMvpActivity<SplashView, SplashPresenter> implements SplashView {

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
