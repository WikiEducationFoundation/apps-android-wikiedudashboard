package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * [Query] model class
 * @constructor primary constructor
 *
 * @property page Map variable with String and MediaDetail type keys
 * ***/
class Query(
    @SerializedName("pages")
    @Expose
    val page: Map<String, MediaDetail>
)
