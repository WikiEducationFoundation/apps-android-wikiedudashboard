package org.wikiedufoundation.wikiedudashboard.course_detail.common.provider

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

interface CourseDetailProvider {
    fun requestCourseDetail(url: String, presenterCallback: PresenterCallback<*>)
}
