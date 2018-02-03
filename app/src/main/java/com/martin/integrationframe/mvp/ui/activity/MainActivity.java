package com.martin.integrationframe.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.martin.integrationframe.R;
import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.mvp.ui.activity.mvp.presenter.MainP;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.MainV;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

@CreatePresenter(MainP.class)
public class MainActivity extends BaseMvpActivity<MainV, MainP> implements MainV {

    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.lv_content)
    ListView lvContent;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(getActivity());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        getPresenter().initTopBar(topBar, getActivity());
        getPresenter().initAdapter(getContext());

        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getPresenter().onItemClick(position, getActivity());
            }
        });
    }

    @Override
    public void onBackPressed() {
        getPresenter().onBackPress(getContext());
    }

    @Override
    public void completeAdapter(ArrayAdapter<String> adapter) {
        lvContent.setAdapter(adapter);
    }
}
