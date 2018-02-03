package com.martin.integrationframe.mvp.ui.activity.functionality;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.martin.integrationframe.R;
import com.martin.integrationframe.base.adapter.ViewPagerFragmentAdapter;
import com.martin.integrationframe.base.mvp.factory.CreatePresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpActivity;
import com.martin.integrationframe.constant.ConstantExtra;
import com.martin.integrationframe.model.ShowImageModel;
import com.martin.integrationframe.mvp.ui.activity.mvp.presenter.ShowImageP;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.ShowImageV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Martin on 2018/2/2 13:40
 * 邮箱：martin0207mfh@163.com
 *
 * @description 展示图片，可以多张展示
 */
@CreatePresenter(ShowImageP.class)
public class ShowImageActivity extends BaseMvpActivity<ShowImageV, ShowImageP> implements ShowImageV {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.img_close)
    ImageView imgClose;

    // ==============  启动方式 ================================================

    public static void start(Context context, String path) {
        ShowImageModel model = new ShowImageModel(path);
        start(context, model);
    }

    public static void start(Context context, Uri uri) {
        ShowImageModel model = new ShowImageModel(uri);
        start(context, model);
    }

    public static void start(Context context, ShowImageModel model) {
        ArrayList<ShowImageModel> list = new ArrayList<>();
        list.add(model);
        start(context, list);
    }

    public static void start(Context context, ArrayList<ShowImageModel> list) {
        Intent starter = new Intent(context, ShowImageActivity.class);
        starter.putParcelableArrayListExtra(ConstantExtra.REQUEST_DATA_LIST, list);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        init();
    }

    private void init() {
        ArrayList<ShowImageModel> list = getIntent().getParcelableArrayListExtra(ConstantExtra.REQUEST_DATA_LIST);
        getPresenter().initPageAdapter(getSupportFragmentManager(), list);
    }

    @Override
    public void showImages(ViewPagerFragmentAdapter adapter) {
        vpContent.setAdapter(adapter);
    }

    @OnClick({R.id.img_close})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.img_close:
                onBackPressed();
                break;
        }
    }
}
