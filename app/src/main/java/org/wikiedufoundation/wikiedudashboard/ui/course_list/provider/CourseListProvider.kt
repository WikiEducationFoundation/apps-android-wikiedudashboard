package org.wikiedufoundation.wikiedudashboard.ui.course_list.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface CourseListProvider {
    fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>)
}
