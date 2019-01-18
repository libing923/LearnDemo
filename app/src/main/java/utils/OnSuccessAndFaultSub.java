package utils;

import android.app.ProgressDialog;
import android.content.Context;

import io.reactivex.observers.DisposableObserver;

public abstract class OnSuccessAndFaultSub<T> extends DisposableObserver<T> {

    /**
     * 是否需要显示默认Loading
     */
    private boolean showProgress = true;

    private ProgressDialog progressDialog;

    public OnSuccessAndFaultSub(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    /**
     * @param context      上下文
     * @param showProgress 是否需要显示默认Loading
     */
    public OnSuccessAndFaultSub(Context context, boolean showProgress) {
        progressDialog = new ProgressDialog(context);
        this.showProgress = showProgress;
    }

    private void showProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.show();
        }
    }


    private void dismissProgressDialog() {
        if (showProgress && null != progressDialog) {
            progressDialog.dismiss();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        progressDialog = null;
    }
}
