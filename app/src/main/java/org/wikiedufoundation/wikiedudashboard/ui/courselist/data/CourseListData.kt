package org.wikiedufoundation.wikiedudashboard.ui.courselist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * [CourseListData] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
@Entity(tableName = "course_list")
class CourseListData(
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
    ) {
    override fun toString(): String {
        return "CourseListData(id=$id, " +
                "title='$title', " +
                "created_at='$createdAt', " +
                "updated_at='$updatedAt', " +
                "start='$start', " +
                "end='$end', " +
                "school='$school', " +
                "term='$term', " +
                "character_sum='$characterSum', " +
                "view_sum='$viewSum', " +
                "user_count='$userCount', " +
                "article_count='$articleCount', " +
                "revision_count='$revisionCount', " +
                "slug='$slug', " +
                "subject='$subject', " +
                "expected_students=$expectedStudents, " +
                "description='$description', " +
                "isSubmitted=$isSubmitted, " +
                "passcode='$passcode', " +
                "timeline_start='$timelineStart', " +
                "timeline_end='$timelineEnd', " +
                "day_exceptions='$dayExceptions', " +
                "weekdays='$weekdays', " +
                "new_article_count=$newArticleCount, " +
                "isNo_day_exceptions=$isNoDayExceptions, " +
                "trained_count=$trainedCount)"
    }
}
