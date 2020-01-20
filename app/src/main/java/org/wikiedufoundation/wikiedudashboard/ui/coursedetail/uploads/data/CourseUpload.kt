package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * [CourseUpload] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class CourseUpload  (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("uploaded_at")
    val uploadedAt: String? = null,
    @SerializedName("usage_count")
    val usageCount: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("thumburl")
    val thumbUrl: String? = null,
    @SerializedName("deleted")
    val deleted: Boolean? = null,
    @SerializedName("thumbwidth")
    val thumbWidth: String? = null,
    @SerializedName("thumbheight")
    val thumbHeight: String? = null,
    @SerializedName("file_name")
    val fileName: String? = null,
    @SerializedName("uploader")
    val uploader: String? = null
    ): Serializable
