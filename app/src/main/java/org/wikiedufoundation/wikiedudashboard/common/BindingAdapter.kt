package org.wikiedufoundation.wikiedudashboard.common

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.wikiedufoundation.wikiedudashboard.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("loadImageUrl")
fun ImageView.setGlideImage(thumbUrl: String?) {
    Glide.with(this)
            .load(thumbUrl)
            .into(this)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("dateToString")
fun TextView.setDate(date: Date?) {
    val dateFormat = SimpleDateFormat(this.context.getString(R.string.time_format_12_hours))
    date?.let { this.text = dateFormat.format(it).toString() }
}