package com.test.test.movieviewer.helpers;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtils {
	static private ProgressDialog mProgress;

    public static void showDialog(String title, Context context) {
        showDialog(title, context, false);
    }

	public static void showDialog(String title, Context context, boolean isCancelable) {
		//Dismiss pre-existing dialogs before creating a new one.
		if ((mProgress != null) && mProgress.isShowing()) {
			mProgress.dismiss();
		}
		mProgress = new ProgressDialog(context);
		mProgress.setMessage(title);
		mProgress.setIcon(android.R.drawable.ic_dialog_info);
		mProgress.setIndeterminate(true);
		mProgress.setCancelable(isCancelable);
		mProgress.show();
	}

    public static void dismissDialog() {
		if ((mProgress != null) && mProgress.isShowing()) {
			mProgress.dismiss();
		}
	}
}
