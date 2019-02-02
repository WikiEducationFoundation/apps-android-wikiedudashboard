package org.wikiedufoundation.wikiedudashboard.helper;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.wikiedufoundation.wikiedudashboard.R;


public final class ViewUtils {

	private ViewUtils() {
		// Never Called
	}


	public static void showSnackbar(View view, String message) {
		Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void hideKeyboard(View view) {
		if (view != null) {
			InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			view.clearFocus();
			if (manager != null) {
				manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		}
	}

	public static void showKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (!imm.isAcceptingText()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		}

	}

	public static void showAlertDialog(Context context, String title, String message) {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(title)
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("Okay", (dialog, which) -> dialog.dismiss()).show();
	}
	public static void showCustomChromeTabs(Context context, String webUrl){
		try {
			Log.d("WEB_URL", webUrl);
			Uri uri = Uri.parse(webUrl);
			CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
			builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
			builder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
			builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			CustomTabsIntent customTabsIntent = builder.build();
			customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			customTabsIntent.launchUrl(context, uri);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
