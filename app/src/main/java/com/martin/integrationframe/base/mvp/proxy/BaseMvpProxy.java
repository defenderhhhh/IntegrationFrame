package com.martin.integrationframe.base.mvp.proxy;

import android.content.Intent;
import android.os.Bundle;

import com.martin.integrationframe.base.mvp.factory.PresenterMvpFactory;
import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpView;


/**
 * 作者：Martin on 2017/12/16 15:26
 * 邮箱：martin0207mfh@163.com
 *
 * @description 代理实现类，用来管理Presenter的生命周期，还有和view之间的关联
 */
public class BaseMvpProxy<V extends BaseMvpView, P extends BaseMvpPresenter<V>> implements PresenterProxyInterface<V, P> {

    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";
    /**
     * Presenter工厂类
     */
    private PresenterMvpFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public BaseMvpProxy(PresenterMvpFactory<V, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getMvpPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    /**
     * 获取Presenter的工厂类
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     * 如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    @Override
    public P getPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        return mPresenter;
    }

    /**
     * 在 onCreate() 里面做绑定，避免出现空指针问题
     * 绑定Presenter和view
     */
    public void onCreate(V mvpView){
        getPresenter();
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.onAttachView(mvpView);
            mIsAttchView = true;
        }
    }

//    /**
//     * 绑定Presenter和view
//     *
//     * @param mvpView
//     */
//    public void onResume(V mvpView) {
//        getPresenter();
//        if (mPresenter != null && !mIsAttchView) {
//            mPresenter.onAttachView(mvpView);
//            mIsAttchView = true;
//        }
//    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachView() {
        if (mPresenter != null && mIsAttchView) {
            mPresenter.onDetachView();
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        if (mPresenter != null) {
            onDetachView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;
    }

    /**
     * 数据请求返回处理
     * @param requestCode   请求码
     * @param resultCode    返回码
     * @param data  返回数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
