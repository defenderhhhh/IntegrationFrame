package com.martin.integrationframe.base.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.martin.integrationframe.base.mvp.ui.BaseMvpView;


/**
 * 作者：Martin on 2017/12/15 22:47
 * 邮箱：martin0207mfh@163.com
 *
 * @description MVP 架构的 Presenter基类,使用MVP结构时,必须继承该类
 * 指定绑定的View层,必须实现 BaseMvpView
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> {

    private static final String TAG = BaseMvpPresenter.class.getSimpleName();

    private V mvpView;

    /**
     * Presenter 被创建之后 调用
     *
     * @param savedState 被意外销毁后的 Bundle
     */
    public void onCreatePresenter(@Nullable Bundle savedState) {
    }

    /**
     * 绑定 V 层
     */
    public void onAttachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 解绑 V 层
     */
    public void onDetachView() {
        this.mvpView = null;
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
    }

    /**
     * 数据请求返回处理
     * @param requestCode   请求码
     * @param resultCode    返回码
     * @param data  返回数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    /**
     * 获取 V 层
     */
    public V getView() {
        return mvpView;
    }
}
