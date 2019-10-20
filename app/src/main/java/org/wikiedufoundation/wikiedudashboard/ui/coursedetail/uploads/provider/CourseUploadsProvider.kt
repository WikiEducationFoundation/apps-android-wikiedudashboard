package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * [CourseUploadsProvider] interface that defines [requestCourseUploads] method
 * ***/
interface CourseUploadsProvider {
    /**
     * Retrofit http request method to get course uploads
     *
     * @param url api url
     * @param presenterCallback PresenterCallback variable
     * ***/
    fun requestCourseUploads(url: String, presenterCallback: PresenterCallback<*>)
}
