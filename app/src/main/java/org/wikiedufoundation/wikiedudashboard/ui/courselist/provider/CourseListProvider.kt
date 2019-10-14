package org.wikiedufoundation.wikiedudashboard.ui.courselist.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Interface that defines [requestCourseList] method
 * ***/
interface CourseListProvider {

    /**
     * Retrofit HTTP request call for course list with cookies
     *
     * @param cookies course list cookies
     * @param presenterCallback Retrofit2 http request call result [PresenterCallback] variable
    * ***/
    fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>)
}
