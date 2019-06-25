package org.wikiedufoundation.wikiedudashboard.ui.courselist.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface CourseListProvider {
    fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>)
}
