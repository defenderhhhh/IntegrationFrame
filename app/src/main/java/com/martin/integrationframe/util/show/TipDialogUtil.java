package com.martin.integrationframe.util.show;

import android.app.Activity;
import android.content.DialogInterface;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * 作者：Martin on 2018/1/30 14:28
 * 邮箱：martin0207mfh@163.com
 */
public class TipDialogUtil {

    public static QMUITipDialog tipDialog;

    // ===============================  loading  =======================================================

    public static void showLoadingDialog(Activity activity) {
        showLoadingDialog(activity, null, true);
    }

    public static void showLoadingDialog(Activity activity, String msg) {
        showLoadingDialog(activity, msg, true);
    }

    public static void showLoadingDialog(Activity activity, int msgRes) {
        if (msgRes <= 0) {
            throw new NullPointerException("the resource of msg must not null !");
        }
        showLoadingDialog(activity, activity.getResources().getString(msgRes));
    }

    public static void showLoadingDialog(Activity activity, boolean cancelable) {
        showLoadingDialog(activity, null, cancelable);
    }

    public static void showLoadingDialog(Activity activity, String msg, boolean cancelable) {
        int iconType = QMUITipDialog.Builder.ICON_TYPE_LOADING;
        String tipWord = msg != null ? msg : "请稍候……";

        showDialog(activity, iconType, tipWord, cancelable);
    }


    // ===============================  success  =======================================================

    public static void showSuccessDialog(Activity activity) {
        showSuccessDialog(activity, null, true);
    }

    public static void showSuccessDialog(Activity activity, String msg) {
        showSuccessDialog(activity, msg, true);
    }

    public static void showSuccessDialog(Activity activity, int msgRes) {
        if (msgRes <= 0) {
            throw new NullPointerException("the resource of msg must not null !");
        }
        showSuccessDialog(activity, activity.getResources().getString(msgRes));
    }

    public static void showSuccessDialog(Activity activity, boolean cancelable) {
        showSuccessDialog(activity, null, cancelable);
    }

    public static void showSuccessDialog(Activity activity, String msg, boolean cancelable) {
        int iconType = QMUITipDialog.Builder.ICON_TYPE_SUCCESS;
        String tipWord = msg != null ? msg : "操作成功";
        showDialog(activity, iconType, tipWord, cancelable);
    }


    // ===============================  error  =======================================================

    public static void showErrorDialog(Activity activity) {
        showErrorDialog(activity, null, true);
    }

    public static void showErrorDialog(Activity activity, String msg) {
        showErrorDialog(activity, msg, true);
    }

    public static void showErrorDialog(Activity activity, int msgRes) {
        if (msgRes <= 0) {
            throw new NullPointerException("the resource of msg must not null !");
        }
        showErrorDialog(activity, activity.getResources().getString(msgRes));
    }

    public static void showErrorDialog(Activity activity, boolean cancelable) {
        showErrorDialog(activity, null, cancelable);
    }

    public static void showErrorDialog(Activity activity, String msg, boolean cancelable) {
        int iconType = QMUITipDialog.Builder.ICON_TYPE_FAIL;
        String tipWord = msg != null ? msg : "操作失败";
        showDialog(activity, iconType, tipWord, cancelable);
    }


    // ======================================  tip  ==========================================================

    public static void showTipDialog(Activity activity) {
        showTipDialog(activity, null, true);
    }

    public static void showTipDialog(Activity activity, String msg) {
        showTipDialog(activity, msg, true);
    }

    public static void showTipDialog(Activity activity, int msgRes) {
        if (msgRes <= 0) {
            throw new NullPointerException("the resource of msg must not null !");
        }
        showTipDialog(activity, activity.getResources().getString(msgRes));
    }

    public static void showTipDialog(Activity activity, boolean cancelable) {
        showTipDialog(activity, null, cancelable);
    }

    public static void showTipDialog(Activity activity, String msg, boolean cancelable) {
        int iconType = QMUITipDialog.Builder.ICON_TYPE_INFO;
        String tipWord = msg != null ? msg : "";
        showDialog(activity, iconType, tipWord, cancelable);
    }

    // ======================================  message  ==========================================================

    public static void showMessageDialog(Activity activity) {
        showMessageDialog(activity, null, true);
    }

    public static void showMessageDialog(Activity activity, String msg) {
        showMessageDialog(activity, msg, true);
    }

    public static void showMessageDialog(Activity activity, int msgRes) {
        if (msgRes <= 0) {
            throw new NullPointerException("the resource of msg must not null !");
        }
        showMessageDialog(activity, activity.getResources().getString(msgRes));
    }

    public static void showMessageDialog(Activity activity, boolean cancelable) {
        showMessageDialog(activity, null, cancelable);
    }

    public static void showMessageDialog(Activity activity, String msg, boolean cancelable) {
        int iconType = QMUITipDialog.Builder.ICON_TYPE_INFO;
        String tipWord = msg != null ? msg : "";
        showDialog(activity, iconType, tipWord, cancelable);
    }

    // ======================================  业务区  ==========================================================

    /**
     * 提取 show
     *
     * @param activity   上下文对象
     * @param cancelable 是否可以关闭
     * @param iconType   图标类型
     * @param tipWord    提醒内容
     */
    private static void showDialog(Activity activity, int iconType, String tipWord, boolean cancelable) {
        tipDialog = new QMUITipDialog.Builder(activity)
                .setIconType(iconType)
                .setTipWord(tipWord)
                .create(cancelable);

        if (!activity.isFinishing() && !activity.isDestroyed()) {
            tipDialog.show();
        }

        setDismissListener();
    }


    /**
     * 关闭 dialog
     */
    public static void dismissDialog() {
        if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.dismiss();
            tipDialog = null;
        }
    }

    /**
     * 设置关闭监听
     */
    private static void setDismissListener() {
        tipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (tipDialog != null) {
                    tipDialog = null;
                }
            }
        });
    }

}
