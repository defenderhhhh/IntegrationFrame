package com.martin.integrationframe.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.mvp.presenter.SplashPresenter;
import com.martin.integrationframe.mvp.view.SplashView;
import com.orhanobut.logger.Logger;

/**
 * 作者：Martin on 2018/1/31 13:25
 * 邮箱：martin0207mfh@163.com
 */
@CreatePresenter(SplashPresenter.class)
public class SplashActivity extends BaseMvpActivity<SplashView, SplashPresenter> implements SplashView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().countDown();
    }

    @Override
    public void countDown(long time) {
        Logger.e("返回的内容  " + time);
        if (time == 0) {
            MainActivity.start(getContext());
            finish();
        }
    }

}
