package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data

import com.google.gson.annotations.SerializedName

/**
 * [Article] model class
 * @constructor primary constructor
 *
 * @property id Int, article id
 * @property title String, article title
 * ***/
class Article(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)
