package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.CourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Course detail presenter to implement [requestCourseDetail] and display data in view
 * @constructor primary constructor with properties
 *
 * @property courseDetailView view for course detail
 * @property courseDetailProvider api service for course detail
 * ***/
class CourseDetailPresenterImpl(private val courseDetailView: CourseDetailView, private val courseDetailProvider: CourseDetailProvider) : CourseDetailPresenter {

    override fun requestCourseDetail(url: String) {
        courseDetailView.showProgressBar(true)
        courseDetailProvider.requestCourseDetail(url, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                courseDetailView.showProgressBar(false)
                courseDetailView.setData((o as CourseDetailResponse).course)
            }

            override fun onFailure() {
                courseDetailView.showProgressBar(false)
                courseDetailView.showMessage("Unable to connect to server.")
            }
        })
    }
}
