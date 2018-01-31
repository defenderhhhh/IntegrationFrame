package com.martin.integrationframe.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.martin.integrationframe.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

/**
 * 作者：Martin on 2018/1/30 15:53
 * 邮箱：martin0207mfh@163.com
 */
public class PhotoUtil {

    private static String authority = "com.martin.integrationframe.fileprovider";

    /**
     * 选择照片,可拍照
     *
     * @param requestCode 请求码
     * @param pickCount   最大选择数量
     * @param takePhoto   是否进行拍照
     *                    返回的内容，可通过 Matisse.obtainResult(data) 来获取图片地址的集合
     */
    public static void pickPhoto(Activity activity, int requestCode, int pickCount, boolean takePhoto) {
        Matisse.from(activity)
                .choose(MimeType.allOf())
                .countable(true)
                .capture(takePhoto)
                .captureStrategy(new CaptureStrategy(true, authority))
                .maxSelectable(pickCount)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(requestCode);
    }

    /**
     * 只拍照
     *
     * @param activity    上下文对象
     * @param requestCode 请求码
     * @return 可以通过 “mediaStoreCompat.getCurrentPhotoPath()” 来获取图片的Uri
     */
    public static MediaStoreCompat takePhoto(Activity activity, int requestCode) {
        MediaStoreCompat mediaStoreCompat = new MediaStoreCompat(activity);
        mediaStoreCompat.setCaptureStrategy(new CaptureStrategy(true, getAuthority()));
        mediaStoreCompat.dispatchCaptureIntent(activity, requestCode);
        return mediaStoreCompat;
    }

    public static String getAuthority() {
        return authority;
    }
}
