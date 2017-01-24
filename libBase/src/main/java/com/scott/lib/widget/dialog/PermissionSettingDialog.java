package com.scott.lib.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import com.scott.lib.constant.Constants;

/**
 * author: heshantao
 * data: 2017/1/24.
 * 权限设置对话框
 */

public class PermissionSettingDialog extends AppCompatDialogFragment {
    private static final String TAG = PermissionSettingDialog.class.getSimpleName();
    private Context mCxt;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCxt = context;
    }

    public static PermissionSettingDialog newInstance(String title, String msg
            , String okStr, String navStr) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DIALOG.TITLE, title);
        bundle.putString(Constants.DIALOG.MSG, msg);
        bundle.putString(Constants.DIALOG.OKSTR, okStr);
        bundle.putString(Constants.DIALOG.NAVSTR, navStr);
        PermissionSettingDialog instance = new PermissionSettingDialog();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        AlertDialog dialog = new AlertDialog.Builder(mCxt)
                .setTitle(bundle.getString(Constants.DIALOG.TITLE))
                .setMessage(bundle.getString(Constants.DIALOG.MSG))
                .setPositiveButton(bundle.getString(Constants.DIALOG.OKSTR), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton(bundle.getString(Constants.DIALOG.NAVSTR), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();

        return dialog;
    }
}
