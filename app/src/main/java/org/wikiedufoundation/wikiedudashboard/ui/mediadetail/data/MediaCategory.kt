package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

import com.google.gson.annotations.SerializedName

/**
 * [MediaCategory] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class MediaCategory(
    @SerializedName("ns")
    val ns: Int,
    @SerializedName("title")
    val title: String
)
