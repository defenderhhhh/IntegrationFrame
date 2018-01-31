package com.martin.integrationframe.greendao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

/**
 * 作者：Martin on 2018/1/31 16:14
 * 邮箱：martin0207mfh@163.com
 */
public class Dao {

    @SuppressLint("StaticFieldLeak")
    private static Dao instance;

    private Context context;

    private SQLiteDatabase db;
    private DaoSession mDaoSession;


    private Dao(Context context) {
        this.context = context;
        setDatabase();
    }

    public static void init(Context context) {
        synchronized (Dao.class) {
            if (instance == null) {
                synchronized (Dao.class) {
                    instance = new Dao(context);
                }
            }
        }
    }

    public static Dao getInstance() {
        return instance;
    }

    public Context getContext() {
        return context;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        MigrationHelper.DEBUG = true;
        UpdateDaoHelper mHelper = new UpdateDaoHelper(context, "zydh-commerce-db", null);
        db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
