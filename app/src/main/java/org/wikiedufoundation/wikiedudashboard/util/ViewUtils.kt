package org.wikiedufoundation.wikiedudashboard.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import org.wikiedufoundation.wikiedudashboard.R
import timber.log.Timber

/**
 * Use [View.showSnackbar] to show snack bar
 *
 * @param message text message in String
 * ***/
fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * Use [Context.showToast] to show a toast
 *
 * @param message text message in String
 * ***/
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Use [View.hideKeyboard] to hide keyboard
 *
 * @param message text message in String
 * ***/
fun View.hideKeyboard() {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    clearFocus()
    manager.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Use [Context.showKeyboard] to show keyboard
 * ***/
fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (!imm.isAcceptingText) {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

/**
 * Use [Context.showAlertDialog] to show AlertDialog
 *
 * @param title alert dialog title in String
 * @param message alert dialog message in String
 * ***/
fun Context.showAlertDialog(title: String, message: String) {

    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Okay") { dialog, _ -> dialog.dismiss() }.show()
}

/**
 * Use [Context.showCustomChromeTabs] to show custom Chrome tabs
 *
 * @param webUrl web url in String
 * ***/
fun Context.showCustomChromeTabs(webUrl: String) {
    try {
        Timber.d(webUrl)
        val uri = Uri.parse(webUrl)
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        builder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        builder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        val customTabsIntent = builder.build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(this, uri)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}