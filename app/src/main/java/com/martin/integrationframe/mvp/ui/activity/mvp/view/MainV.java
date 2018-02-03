package com.martin.integrationframe.mvp.ui.activity.mvp.view;

import android.widget.ArrayAdapter;

import com.martin.integrationframe.base.mvp.ui.BaseMvpView;

/**
 * 作者：Martin on 2018/1/31 22:08
 * 邮箱：martin0207mfh@163.com
 */
public interface MainV extends BaseMvpView {

    void completeAdapter(ArrayAdapter<String> adapter);

}
