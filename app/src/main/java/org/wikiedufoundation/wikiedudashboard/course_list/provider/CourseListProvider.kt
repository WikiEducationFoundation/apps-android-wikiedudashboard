package org.wikiedufoundation.wikiedudashboard.course_list.provider

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

interface CourseListProvider {
    fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>)
}
