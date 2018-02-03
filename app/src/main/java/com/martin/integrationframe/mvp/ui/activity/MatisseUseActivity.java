package com.martin.integrationframe.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.martin.integrationframe.R;
import com.martin.integrationframe.adapter.SingleImgAdapter;
import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.mvp.ui.activity.mvp.presenter.MatisseUseP;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.MatisseUseV;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@CreatePresenter(MatisseUseP.class)
public class MatisseUseActivity extends BaseMvpActivity<MatisseUseV, MatisseUseP> implements MatisseUseV {


    @BindView(R.id.top_bar)
    QMUITopBar topBar;
    @BindView(R.id.btn_pick)
    Button btnPick;
    @BindView(R.id.btn_pick_or_take)
    Button btnPickOrTake;
    @BindView(R.id.btn_take)
    Button btnTake;
    @BindView(R.id.gv_content)
    GridView gvContent;

    private SingleImgAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MatisseUseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(getActivity());
        setContentView(R.layout.activity_matisse_use);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        getPresenter().initTopBar(topBar);

        adapter = new SingleImgAdapter(null, getContext());
        gvContent.setAdapter(adapter);

        gvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getPresenter().onItemClick(adapter, position);
            }
        });
    }


    @OnClick({R.id.btn_pick, R.id.btn_pick_or_take, R.id.btn_take})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pick:
                getPresenter().onPickPress(getActivity());
                break;
            case R.id.btn_pick_or_take:
                getPresenter().onPickOrTake(getActivity());
                break;
            case R.id.btn_take:
                getPresenter().onTakePress(getActivity());
                break;
        }
    }

    @Override
    public void refresh(List<Uri> list) {
        adapter.refreshRes(list);
    }

    @Override
    public void addRes(Uri uri) {
        adapter.addRes(uri);
    }

    @Override
    public void onBackPress() {
        onBackPressed();
    }
}
