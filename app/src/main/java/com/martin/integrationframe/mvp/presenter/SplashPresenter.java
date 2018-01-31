package com.martin.integrationframe.mvp.presenter;

import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.mvp.model.SplashModel;
import com.martin.integrationframe.mvp.view.SplashView;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：Martin on 2018/1/31 13:31
 * 邮箱：martin0207mfh@163.com
 */
public class SplashPresenter extends BaseMvpPresenter<SplashView> {


    private Disposable subscribe;

    public void countDown() {
        subscribe = SplashModel.countDown(5)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Logger.e("这里执行了多少次 " + aLong);
                        getView().countDown(aLong);
                    }
                });
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        if (!subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
