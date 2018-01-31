package com.martin.integrationframe.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Martin on 2018/1/31 15:46
 * 邮箱：martin0207mfh@163.com
 */
@Entity
public class DaoModel {

    private String test;

    @Generated(hash = 482115557)
    public DaoModel(String test) {
        this.test = test;
    }

    @Generated(hash = 528835260)
    public DaoModel() {
    }

    public String getTest() {
        return this.test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
