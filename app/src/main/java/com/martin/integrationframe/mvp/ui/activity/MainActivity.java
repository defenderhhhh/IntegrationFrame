package com.martin.integrationframe.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.martin.integrationframe.R;
import com.martin.integrationframe.base.mvc.BaseActivity;
import com.martin.integrationframe.util.PhotoUtil;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_PHOTO_PICK = 100;
    private static final int REQUEST_PHOTO_TAKE = 101;

    @BindView(R.id.btn_pick_photo)
    Button btnPickPhoto;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.iv_img)
    ImageView ivImg;


    private MediaStoreCompat mediaStoreCompat;


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new RxPermissions(getActivity())
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe();
    }

    @OnClick({R.id.btn_pick_photo, R.id.btn_take_photo})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_pick_photo:
                PhotoUtil.pickPhoto(getActivity(), REQUEST_PHOTO_PICK, 1, false);
                break;
            case R.id.btn_take_photo:
                mediaStoreCompat = PhotoUtil.takePhoto(getActivity(), REQUEST_PHOTO_TAKE);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PHOTO_PICK:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (uris != null && uris.size() > 0) {
                        Logger.e("获取到的图片 = " + uris.get(0).getPath());
                        Glide.with(getContext())
                                .load(uris.get(0))
                                .into(ivImg);
                    } else {
                        Logger.e("没有获取到图片");
                    }
                    break;
                case REQUEST_PHOTO_TAKE:
                    Uri uri = mediaStoreCompat.getCurrentPhotoPath();
                    Glide.with(getContext())
                            .load(uri)
                            .into(ivImg);
                    break;
            }
        }
    }
}
