package com.martin.integrationframe.mvp.ui.activity.mvp.view;

import android.net.Uri;

import com.martin.integrationframe.base.mvp.ui.BaseMvpView;

import java.util.List;

/**
 * 作者：Martin on 2018/2/2 14:46
 * 邮箱：martin0207mfh@163.com
 */
public interface MatisseUseV extends BaseMvpView {

    void refresh(List<Uri> list);

    void addRes(Uri uri);

    void onBackPress();

}
