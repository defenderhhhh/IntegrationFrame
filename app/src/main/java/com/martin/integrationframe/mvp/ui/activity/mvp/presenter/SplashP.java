package com.martin.integrationframe.mvp.ui.activity.mvp.presenter;

import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.mvp.ui.activity.mvp.model.tool.SplashModel;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.SplashV;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：Martin on 2018/1/31 13:31
 * 邮箱：martin0207mfh@163.com
 */
public class SplashP extends BaseMvpPresenter<SplashV> {

    public void countdown() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getView().countDownFinish();
                    }
                });
    }

}
