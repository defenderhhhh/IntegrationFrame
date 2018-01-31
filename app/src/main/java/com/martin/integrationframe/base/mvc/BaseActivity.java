package com.martin.integrationframe.base.mvc;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.martin.integrationframe.util.show.KeyboardUtils;
import com.martin.integrationframe.util.show.ProgressDialogUtils;
import com.martin.integrationframe.util.show.ToastUtils;
import com.orhanobut.logger.Logger;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.addActivity(this);
        Logger.e( "The activity that opens is " + getClass().getSimpleName());
    }

    public Context getContext() {
        return this;
    }

    public BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        //设置为竖屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BaseApplication.deleteActivity(this.getClass().getSimpleName());
        ProgressDialogUtils.getInstance().hideProgress();
    }

    protected void showKeyboard(boolean toShow) {
        KeyboardUtils.showKeyboard(getActivity(), toShow);
    }

    /**
     * 延时弹出键盘
     */
    protected void showKeyboardDelayed(View focus) {
        KeyboardUtils.showKeyboardDelayed(getActivity(), focus);
    }

    public void showToast(String message) {
        showToast(message, true);
    }

    public void showToast(String message, boolean isShort) {
        if (isShort) {
            ToastUtils.showToast(getContext(), message);
        } else {
            ToastUtils.showToastLong(getContext(), message);
        }
    }

    public void showToast(int res) {
        showToast(getResources().getString(res));
    }

    /**
     * 设置导航栏颜色
     */
    public void setWindowStatusBarColor(int colorResId) {
        QMUIStatusBarHelper.translucent(getActivity(), colorResId);
    }

    public String noNull(String s) {
        return s == null ? "" : s;
    }

    public String getText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString().trim();
        } else return "";
    }

    public String getText(TextView textView) {
        if (textView != null) {
            return textView.getText().toString().trim();
        } else return "";
    }

}
