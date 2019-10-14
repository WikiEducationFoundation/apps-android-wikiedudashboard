package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter

/**
 * Presenter interface that defines [requestCourseUploads]
 * ***/
interface CourseUploadsPresenter {

    /**
     * [requestCourseUploads] handles course uploads and display in view
     * @param url api url
     * ***/
    fun requestCourseUploads(url: String)
}
