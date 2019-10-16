package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

import com.google.gson.annotations.SerializedName

/**
 * [FileUsage] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class FileUsage(
        @SerializedName("title")
        val title: String,
        @SerializedName("wiki")
        val wiki: String,
        @SerializedName("url")
        val url: String
)