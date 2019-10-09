package org.wikiedufoundation.wikiedudashboard.common

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.wikiedufoundation.wikiedudashboard.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [KotlinFile].
 * It's responsible for custom bindings that are not allowed by default in Android Studio
 */


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