package com.martin.integrationframe.mvp.ui.activity.mvp.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.martin.integrationframe.R;
import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.mvp.ui.activity.MatisseUseActivity;
import com.martin.integrationframe.mvp.ui.activity.TestActivity;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.MainV;
import com.martin.integrationframe.util.systemUtil.PhoneUtil;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 作者：Martin on 2018/1/31 22:09
 * 邮箱：martin0207mfh@163.com
 */
public class MainP extends BaseMvpPresenter<MainV> {

    private QMUIListPopup qmuiListPopup;

    @Override
    public void initTopBar(QMUITopBar topBar, final Activity activity) {
        topBar.setTitle("Function list");
        final QMUIAlphaImageButton btnMore = topBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_more_button);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(activity, btnMore);
            }
        });
    }

    /**
     * 初始化 Adapter
     */
    public void initAdapter(Context context) {
        String[] items = new String[]{
                "Matisse use",
                "Test"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.simple_list_item, items);
        getView().completeAdapter(adapter);
    }

    /**
     * 列表点击事件 处理
     */
    public void onItemClick(int position, Activity activity) {
        switch (position) {
            case 0:
                MatisseUseActivity.start(activity);
                break;
            case 1:
                TestActivity.start(activity);
                break;
        }
    }


    /**
     * 展示 popupWindow
     */
    private void showPopup(final Activity activity, QMUIAlphaImageButton btnMore) {
        if (qmuiListPopup == null) {
            String[] listPopupItems = new String[]{
                    "See Device and Mac"
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.simple_list_item, listPopupItems);

            qmuiListPopup = new QMUIListPopup(activity, QMUIListPopup.DIRECTION_BOTTOM, adapter);

            qmuiListPopup.create(QMUIDisplayHelper.dpToPx(160)
                    , QMUIDisplayHelper.dpToPx(200), new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            qmuiListPopup.dismiss();
                            switch (position) {
                                case 0:
                                    showDeviceMac(activity);
                                    break;
                            }
                        }
                    });
        }
        if (!activity.isFinishing()) {
            qmuiListPopup.show(btnMore);
        }
    }

    /**
     * 展示 Device 和 Mac 信息
     */
    private void showDeviceMac(final Activity activity) {

        new RxPermissions(activity)
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            new QMUIDialog.MessageDialogBuilder(activity)
                                    .setMessage(PhoneUtil.getDeviceInfo(activity))
                                    .setTitle("手机信息")
                                    .addAction("确定", new QMUIDialogAction.ActionListener() {
                                        @Override
                                        public void onClick(QMUIDialog dialog, int index) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }
                });


    }

    /**
     * 返回键内容
     */
    public void onBackPress(Context context) {
        //实现Home键效果
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }

}
