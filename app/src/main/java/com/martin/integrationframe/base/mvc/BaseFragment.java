package com.martin.integrationframe.base.mvc;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.martin.integrationframe.util.show.KeyboardUtils;
import com.martin.integrationframe.util.show.ProgressDialogUtils;
import com.martin.integrationframe.util.show.ToastUtils;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * 作者：Martin on 2017/12/5 17:24
 * 邮箱：martin0207mfh@163.com
 */
public class BaseFragment extends RxFragment {


    protected void showKeyboard(boolean toShow) {
        KeyboardUtils.showKeyboard(getActivity(), toShow);
    }

    /**
     * 延时弹出键盘
     */
    protected void showKeyboardDelayed(View focus) {
        KeyboardUtils.showKeyboardDelayed(getActivity(), focus);
    }

    public void showProgress() {
        showProgress(null, true);
    }

    public void showProgress(boolean cancelAble) {
        showProgress(null, cancelAble);
    }

    public void showProgress(String message) {
        showProgress(message, true);
    }

    public void showProgress(String message, boolean cancelAble) {
        ProgressDialogUtils.getInstance().showProgress(getActivity(), message, cancelAble);
    }

    public void hideProgress() {
        ProgressDialogUtils.getInstance().hideProgress();
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
