package org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface CourseDetailProvider {
    fun requestCourseDetail(url: String, presenterCallback: PresenterCallback<*>)
}
