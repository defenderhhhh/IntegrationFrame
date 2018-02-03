package com.martin.integrationframe.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.integrationframe.R;
import com.martin.integrationframe.base.mvc.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class TestActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_test);
    }
}
