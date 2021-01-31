package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload

/**
 *Declares CourseUploadsRepository interface
 * */
interface CourseUploadsRepository {

    /**
     *Creates a suspend function [requestCourseUploads]
     * */
    suspend fun requestCourseUploads(url: String): List<CourseUpload>
}
