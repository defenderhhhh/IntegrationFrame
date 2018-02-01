package com.martin.integrationframe;

import com.martin.integrationframe.base.mvc.BaseApplication;
import com.martin.integrationframe.greendao.Dao;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * 作者：Martin on 2018/1/30 11:16
 * 邮箱：martin0207mfh@163.com
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //做内容的初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //日志管理工具 Logger 的初始化
        initLogger();
        //GreenDao 的初始化
        Dao.init(getInstance());
        //友盟初始化
        initUM();
    }

    /**
     * 友盟初始化
     */
    private void initUM() {
        UMConfigure.init(getInstance(),"5a72bae3b27b0a2b080000f8","wangzhan",UMConfigure.DEVICE_TYPE_PHONE,"");
        MobclickAgent.setScenarioType(getInstance(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
    }


    /**
     * Logger 初始化
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .tag("martin tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
