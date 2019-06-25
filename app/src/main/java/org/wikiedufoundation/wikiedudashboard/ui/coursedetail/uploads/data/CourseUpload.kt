package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class CourseUpload : Serializable{
    val id: Int? = null
    val uploaded_at: String? = null
    val usage_count: String? = null
    val url: String? = null
    @field:SerializedName("thumburl")
    val thumbUrl: String? = null
    val deleted: Boolean? = null
    @field:SerializedName("thumbwidth")
    val thumbWidth: String? = null
    @field:SerializedName("thumbheight")
    val thumbHeight: String? = null
    val file_name: String? = null
    val uploader: String? = null
}
