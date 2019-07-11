package org.wikiedufoundation.wikiedudashboard.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import org.wikiedufoundation.wikiedudashboard.R

object ViewUtils {

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun hideKeyboard(view: View?) {
        if (view != null) {
            val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            view.clearFocus()
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!imm.isAcceptingText) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    fun showAlertDialog(context: Context, title: String, message: String) {

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Okay") { dialog, _ -> dialog.dismiss() }.show()
    }

    fun showCustomChromeTabs(context: Context, webUrl: String) {
        try {
            Log.d("WEB_URL", webUrl)
            val uri = Uri.parse(webUrl)
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            builder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            val customTabsIntent = builder.build()
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            customTabsIntent.launchUrl(context, uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
} // Never Called
