package org.wikiedufoundation.wikiedudashboard.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.wikiedufoundation.wikiedudashboard.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Use Kotlin [ExtensionFunctionType] to use [showSnackbar] as a view extension function.
 * @param message to show on the Snackbar
 ***/
fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * Use Kotlin [ExtensionFunctionType] to use [showToast] as a context extension function.
 * @param message to show on the toast
 ***/
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

 /**
 * Use Kotlin [ExtensionFunctionType] to use [hideKeyboard] as a view extension function.
 ***/
fun View.hideKeyboard() {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    clearFocus()
    manager.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Use Kotlin [ExtensionFunctionType] to use [showKeyboard] as a view extension function.
 ***/
fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (!imm.isAcceptingText) {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

/**
 * Use Kotlin [ExtensionFunctionType] to use [showAlertDialog] as a context extension function.
 * @param title to add to dialog title
 * @param message
 ***/
fun Context.showAlertDialog(title: String, message: String) {

    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Okay") { dialog, _ -> dialog.dismiss() }.show()
}

/**
 * Use Kotlin [ExtensionFunctionType] to use [showCustomChromeTabs] as a context extension function.
 * @param webUrl
 ***/
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

/**
 * Use Kotlin [ExtensionFunctionType] to load the image with [BindingAdapter]
 ***/
@BindingAdapter("loadImageUrl")
fun ImageView.setGlideImage(thumbUrl: String?) {
    Glide.with(this)
            .load(thumbUrl)
            .into(this)
}

/**
 * Use Kotlin [ExtensionFunctionType] to load format and show the date with [BindingAdapter]
 ***/
@SuppressLint("SimpleDateFormat")
@BindingAdapter("dateToString")
fun TextView.setDate(date: Date?) {
    val dateFormat = SimpleDateFormat(this.context.getString(R.string.time_format_12_hours))
    date?.let { this.text = dateFormat.format(it).toString() }
}