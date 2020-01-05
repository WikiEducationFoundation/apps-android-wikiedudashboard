package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.CourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Use [CourseUploadsPresenterImpl] to handle data and display it in view
 * @constructor primary constructor
 *
 * @property courseUploadsView
 * @property courseUploadsProvider
 * ***/
class CourseUploadsPresenterImpl(
        private val courseUploadsView: CourseUploadsView,
        private val courseUploadsProvider: CourseUploadsProvider
) : CourseUploadsPresenter {

    override fun requestCourseUploads(url: String) {
        courseUploadsView.showProgressBar(true)
        courseUploadsProvider.requestCourseUploads(url, object : PresenterCallback<CourseUploadResponse> {
            override fun onSuccess(courseUploadResponse: CourseUploadResponse) {
                courseUploadsView.showProgressBar(false)
                courseUploadsView.setData((courseUploadResponse).course)
            }

            override fun onFailure() {
                courseUploadsView.showProgressBar(false)
                courseUploadsView.showMessage("Unable to connect to server.")
            }
        })
    }
}
