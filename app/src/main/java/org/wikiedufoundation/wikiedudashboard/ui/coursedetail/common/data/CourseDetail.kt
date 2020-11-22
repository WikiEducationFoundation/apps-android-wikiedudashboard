package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * [CourseDetail] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class CourseDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("school")
    val school: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("isSubmitted")
    val isSubmitted: Boolean,
    @SerializedName("expected_students")
    val expectedStudents: Int,
    @SerializedName("timeline_start")
    val timelineStart: String,
    @SerializedName("timeline_end")
    val timelineEnd: String,
    @SerializedName("day_exceptions")
    val dayExceptions: String,
    @SerializedName("weekdays")
    val weekdays: String,
    @SerializedName("isNo_day_exceptions")
    val isNoDayExceptions: Boolean,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("string_prefix")
    val stringPrefix: String,
    @SerializedName("isUse_start_and_end_times")
    val isUseStartAndEndTimes: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("character_sum")
    val characterSum: Int,
    @SerializedName("upload_count")
    val uploadCount: Int,
    @SerializedName("uploads_in_use_count")
    val uploadsInuseCount: Int,
    @SerializedName("upload_usages_count")
    val uploadUsagesCount: Int,
    @SerializedName("cloned_status")
    val clonedStatus: Int,
    @SerializedName("level")
    val level: String,
    @SerializedName("training_library_slug")
    val trainingLibrarySlug: String,
    @SerializedName("isTimeline_enabled")
    val isTimelineEnabled: Boolean,
    @SerializedName("isHome_wiki_edits_enabled")
    val isHomeWikiEditsEnabled: Boolean,
    @SerializedName("isWiki_edits_enabled")
    val isWikiEditsEnabled: Boolean,
    @SerializedName("isAssignment_edits_enabled")
    val isAssignmentEditsEnabled: Boolean,
    @SerializedName("isWiki_course_page_enabled")
    val isWikiCoursePageEnabled: Boolean,
    @SerializedName("isEnrollment_edits_enabled")
    val isEnrollmentEditsEnabled: Boolean,
    @SerializedName("isAccount_requests_enabled")
    val isAccountRequestsEnabled: Boolean,
    @SerializedName("term")
    val term: String,
    @SerializedName("isLegacy")
    val isLegacy: Boolean,
    @SerializedName("isEnded")
    val isEnded: Boolean,
    @SerializedName("isPublished")
    val isPublished: Boolean,
    @SerializedName("isClosed")
    val isClosed: Boolean,
    @SerializedName("enroll_url")
    val enrollUrl: String,
    @SerializedName("created_count")
    val createdCount: String,
    @SerializedName("edited_count")
    val editedCount: String,
    @SerializedName("edit_count")
    val editCount: String,
    @SerializedName("student_count")
    val studentCount: Int,
    @SerializedName("trained_count")
    val trainedCount: Int,
    @SerializedName("word_count")
    val wordCount: String,
    @SerializedName("view_count")
    val viewCount: String,
    @SerializedName("character_sum_human")
    val characterSumHuman: String,
    @SerializedName("passcode")
    val passCode: String,
    @SerializedName("isCanUploadSyllabus")
    val isCanUploadSyllabus: Boolean
) : Serializable
