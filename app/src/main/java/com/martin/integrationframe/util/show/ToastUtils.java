package com.martin.integrationframe.util.show;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.martin.integrationframe.MyApplication;
import com.martin.integrationframe.util.HandlerUtils;

/**
 * 作者：Martin on 2017/12/5 14:24
 * 邮箱：martin0207mfh@163.com
 */
public class ToastUtils {

    private static Toast toast;

    public static void show(String msg) {
        showToast(MyApplication.getInstance(), msg);
    }

    public static void show(int msgRes) {
        showToast(MyApplication.getInstance(), MyApplication.getInstance().getResources().getString(msgRes));
    }

    @SuppressLint("ShowToast")
    public static void showToast(final Context context, final String text) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.show();
        } else {
            HandlerUtils.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
                    }
                    toast.setText(text);
                    toast.show();
                }
            });
        }
    }

    @SuppressLint("ShowToast")
    public static void showToastLong(final Context context, final String text) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
            }
            toast.setText(text);
            toast.show();
        } else {
            HandlerUtils.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
                    }
                    toast.setText(text);
                    toast.show();
                }
            });
        }
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
