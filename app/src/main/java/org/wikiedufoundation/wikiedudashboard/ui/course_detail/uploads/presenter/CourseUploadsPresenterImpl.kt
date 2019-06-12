package org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.presenter


import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.provider.CourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.view.CourseUploadsView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class CourseUploadsPresenterImpl(private val courseUploadsView: CourseUploadsView, private val courseUploadsProvider: CourseUploadsProvider) : CourseUploadsPresenter {

    override fun requestCourseUploads(url: String) {
        courseUploadsView.showProgressBar(true)
        courseUploadsProvider.requestCourseUploads(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                courseUploadsView.showProgressBar(false)
                courseUploadsView.setData((o as CourseUploadResponse).course)
            }

            override fun onFailure() {
                courseUploadsView.showProgressBar(false)
                courseUploadsView.showMessage("Unable to connect to server.")
            }
        })
    }
}
