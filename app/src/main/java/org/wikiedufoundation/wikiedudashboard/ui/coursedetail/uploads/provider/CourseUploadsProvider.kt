package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface CourseUploadsProvider {
    fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<*>)
}
