package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

interface CourseUploadsProvider {
    fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<*>)
}
