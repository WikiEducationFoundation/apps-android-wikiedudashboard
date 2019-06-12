package org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.presenter

import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.provider.CourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.view.CourseDetailView
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

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
