package com.yiya.foryou.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import io.reactivex.annotations.Nullable;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/1/16	15:08
 * description:
 */
public class NormalDialog extends DialogFragment {
    private DialogBackCall dialogBackCall;
    private String title;
    private String message;

    public interface DialogBackCall {
        void onConfirm();

        void onCancel();
    }

    public void setDialogBackCall(DialogBackCall dialogBackCall) {
        this.dialogBackCall = dialogBackCall;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            title = getArguments().getString("title");
            message = getArguments().getString("message");
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", (dialog12, id) -> dialogBackCall.onConfirm())
                .setNegativeButton("取消", (dialog1, id) -> dialogBackCall.onCancel())
                .setCancelable(false);

        return dialog.create();
    }
}
