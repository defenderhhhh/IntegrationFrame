package com.martin.integrationframe.mvp.ui.activity.mvp.model.tool;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 作者：Martin on 2018/1/31 14:02
 * 邮箱：martin0207mfh@163.com
 */
public class SplashModel {

    /**
     * 计时功能
     *
     * @param count 计时秒数
     */
    public static Observable<Long> countdown(int count) {
        return Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(count)
                .subscribeOn(AndroidSchedulers.mainThread());
    }

}
