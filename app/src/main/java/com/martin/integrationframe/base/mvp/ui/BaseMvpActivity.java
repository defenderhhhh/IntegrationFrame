package com.martin.integrationframe.base.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.integrationframe.base.mvc.BaseActivity;
import com.martin.integrationframe.base.mvp.factory.PresenterMvpFactory;
import com.martin.integrationframe.base.mvp.factory.PresenterMvpFactoryImpl;
import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.base.mvp.proxy.BaseMvpProxy;
import com.martin.integrationframe.base.mvp.proxy.PresenterProxyInterface;


/**
 * 作者：Martin on 2017/12/15 22:55
 * 邮箱：martin0207mfh@163.com
 */
public abstract class BaseMvpActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends BaseActivity
        implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


    //==========================  声明周期  ========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }
}
