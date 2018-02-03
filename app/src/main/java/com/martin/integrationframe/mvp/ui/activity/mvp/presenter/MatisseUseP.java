package com.martin.integrationframe.mvp.ui.activity.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.martin.integrationframe.adapter.SingleImgAdapter;
import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.constant.ConstantCode;
import com.martin.integrationframe.model.ShowImageModel;
import com.martin.integrationframe.mvp.ui.activity.functionality.ShowImageActivity;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.MatisseUseV;
import com.martin.integrationframe.util.FileUtils;
import com.martin.integrationframe.util.PhotoUtil;
import com.martin.integrationframe.util.systemUtil.PhoneUtil;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Martin on 2018/2/2 14:46
 * 邮箱：martin0207mfh@163.com
 */
public class MatisseUseP extends BaseMvpPresenter<MatisseUseV> {

    private MediaStoreCompat mediaStoreCompat;

    public void initTopBar(QMUITopBar topBar) {
        if (topBar == null) {
            return;
        }

        topBar.setTitle("Matisse Use");

        topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().onBackPress();
            }
        });
    }

    public void onPickPress(Activity activity) {
        PhotoUtil.pickPhoto(activity, ConstantCode.REQUEST_PHOTO_PICK, 9, false);
    }

    public void onPickOrTake(Activity activity) {
        PhotoUtil.pickPhoto(activity, ConstantCode.REQUEST_PHOTO_PICK, 6, true);
    }

    public void onTakePress(Activity activity) {
        mediaStoreCompat = PhotoUtil.takePhoto(activity, ConstantCode.REQUEST_PHOTO_TAKE);
    }

    public void onItemClick(SingleImgAdapter adapter, int position) {
        List<Uri> data = adapter.getData();
        if (data == null || data.size() == 0) {
            return;
        }
        ArrayList<ShowImageModel> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(new ShowImageModel(data.get(i)));
        }
        ShowImageActivity.start(adapter.getContext(), list, position);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ConstantCode.RESULT_OK) {
            switch (requestCode) {
                case ConstantCode.REQUEST_PHOTO_PICK:
                    List<Uri> uris = Matisse.obtainResult(data);
                    getView().refresh(uris);
                    break;
                case ConstantCode.REQUEST_PHOTO_TAKE:
                    if (mediaStoreCompat != null) {
                        Uri currentPhotoPath = mediaStoreCompat.getCurrentPhotoPath();
                        getView().addRes(currentPhotoPath);
                    }
                    break;
            }
        }
    }
}
