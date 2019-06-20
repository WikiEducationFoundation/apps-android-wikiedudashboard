package org.wikiedufoundation.wikiedudashboard.ui.media_detail.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Query (
    @SerializedName("pages")
    @Expose
    val page: Map<String, MediaDetail>
)
