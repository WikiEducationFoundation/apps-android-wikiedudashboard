package org.wikiedufoundation.wikiedudashboard.ui.profile.data

import com.google.gson.annotations.SerializedName
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import java.io.Serializable

/**
 * [ProfileResponse] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class ProfileResponse : Serializable {
    @SerializedName("user_recent_uploads")
    val uploads: List<CourseUpload>? = null
    @SerializedName("courses_details")
    val courses: List<CourseData>? = null
    @SerializedName("as_instructor")
    val asInstructor: AsInstructorDetails? = null
    @SerializedName("by_students")
    val byStudents: ByStudentsDetails? = null
    @SerializedName("as_student")
    val asStudent: AsStudentDetails? = null
}

/**
 * [CourseData] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class CourseData(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("course_title")
    val courseTitle: String,
    @SerializedName("course_school")
    val courseSchool: String,
    @SerializedName("course_term")
    val courseTerm: String,
    @SerializedName("user_count")
    val userCount: Int,
    @SerializedName("user_role")
    val userRole: String,
    @SerializedName("course_slug")
    val courseSlug: String
) : Serializable

/**
 * [AsInstructorDetails] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class AsInstructorDetails(
    @SerializedName("course_string_prefix")
    val courseStringPrefix: String,
    @SerializedName("courses_count")
    val coursesCount: Int,
    @SerializedName("user_count")
    val userCount: Int,
    @SerializedName("trained_percent")
    val trainedPercent: String
) : Serializable

/**
 * [ByStudentsDetails] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class ByStudentsDetails(
    @SerializedName("word_count")
    val wordCount: String,
    @SerializedName("references_count")
    val referencesCount: String,
    @SerializedName("view_sum")
    val viewSum: String,
    @SerializedName("article_count")
    val articleCount: String,
    @SerializedName("new_article_count")
    val newArticleCount: String,
    @SerializedName("upload_count")
    val uploadCount: String,
    @SerializedName("uploads_in_use_count")
    val uploadsInUseCount: Int,
    @SerializedName("upload_usage_count")
    val uploadUsageCount: Int
) : Serializable

/**
 * [AsStudentDetails] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class AsStudentDetails(
    @SerializedName("course_string_prefix")
    val courseStringPrefix: String,
    @SerializedName("individual_courses_count")
    val individualCoursesCount: Int,
    @SerializedName("individual_word_count")
    val individualWordCount: String,
    @SerializedName("individual_references_count")
    val individualReferencesCount: String,
    @SerializedName("individual_article_views")
    val individualArticleViews: String,
    @SerializedName("individual_article_count")
    val individualArticleCount: String,
    @SerializedName("individual_articles_created")
    val individualArticlesCreated: String,
    @SerializedName("individual_upload_count")
    val individualUploadCount: String,
    @SerializedName("individual_upload_usage_count")
    val individualUploadUsageCount: String
) : Serializable