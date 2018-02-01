package com.martin.integrationframe.mvp.presenter;

import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.mvp.model.SplashModel;
import com.martin.integrationframe.mvp.view.SplashView;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Martin on 2018/1/31 13:31
 * 邮箱：martin0207mfh@163.com
 */
public class SplashPresenter extends BaseMvpPresenter<SplashView> {

    public void countdown() {

        SplashModel.countdown(2)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.e("这是第" + aLong + "次发送");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getView().countDownFinish();
                    }
                });
    }

}
