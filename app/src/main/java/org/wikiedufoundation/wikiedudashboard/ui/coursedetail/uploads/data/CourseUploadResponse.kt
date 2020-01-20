package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data

/**
 * [CourseUploadResponse] response model class
 * @constructor primary constructor
 *
 * @property course list of course uploads data from API response
 * ***/
data class CourseUploadResponse(
    val course: CourseUploadList
)