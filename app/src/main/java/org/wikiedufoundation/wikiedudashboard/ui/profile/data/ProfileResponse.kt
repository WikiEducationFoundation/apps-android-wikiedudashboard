package org.wikiedufoundation.wikiedudashboard.ui.profile.data

import com.google.gson.annotations.SerializedName
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import java.io.Serializable

class ProfileResponse : Serializable {
    @SerializedName("user_recent_uploads")
    val uploads: List<CourseUpload>? = null
    @SerializedName("courses_details")
    val courses: List<CourseData>? = null
    val as_instructor: AsInstructorDetails? = null
    val by_students: ByStudentsDetails? = null
    val as_student: AsStudentDetails? = null
}

class CourseData(
        val course_id: Int,
        val course_title: String,
        val course_school: String,
        val course_term: String,
        val user_count: Int,
        val user_role: String,
        val course_slug: String
) : Serializable


class AsInstructorDetails(
        val course_string_prefix: String,
        val courses_count: Int,
        val user_count: Int,
        val trained_percent: String
) : Serializable

class ByStudentsDetails(
        val word_count: String,
        val references_count: String,
        val view_sum: String,
        val article_count: String,
        val new_article_count: String,
        val upload_count: String,
        val uploads_in_use_count: Int,
        val upload_usage_count: Int
) : Serializable

class AsStudentDetails(
        val course_string_prefix: String,
        val individual_courses_count: Int,
        val individual_word_count: String,
        val individual_references_count: String,
        val individual_article_views: String,
        val individual_article_count: String,
        val individual_articles_created: String,
        val individual_upload_count: String,
        val individual_upload_usage_count: String
) : Serializable