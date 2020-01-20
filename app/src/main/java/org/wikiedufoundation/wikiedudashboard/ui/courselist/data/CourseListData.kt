package org.wikiedufoundation.wikiedudashboard.ui.courselist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * [CourseListData] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
@Entity(tableName = "course_list")
data class CourseListData(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("start")
    val start: String?,
    @SerializedName("end")
    val end: String?,
    @SerializedName("school")
    val school: String?,
    @SerializedName("term")
    val term: String?,
    @SerializedName("character_sum")
    val characterSum: String?,
    @SerializedName("view_sum")
    val viewSum: String?,
    @SerializedName("user_count")
    val userCount: String?,
    @SerializedName("article_count")
    val articleCount: String?,
    @SerializedName("revision_count")
    val revisionCount: String?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("subject")
    val subject: String?,
    @SerializedName("expected_students")
    val expectedStudents: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("isSubmitted")
    val isSubmitted: Boolean?,
    @SerializedName("passcode")
    val passcode: String?,
    @SerializedName("timeline_start")
    val timelineStart: String?,
    @SerializedName("timeline_end")
    val timelineEnd: String?,
    @SerializedName("day_exceptions")
    val dayExceptions: String?,
    @SerializedName("weekdays")
    val weekdays: String?,
    @SerializedName("new_article_count")
    val newArticleCount: Int?,
    @SerializedName("isNo_day_exceptions")
    val isNoDayExceptions: Boolean?,
    @SerializedName("trained_count")
    val trainedCount: Int?
)
