package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter

/**
 * [CourseDetailPresenter] interface defining [requestCourseDetail]
 * ***/
interface CourseDetailPresenter {
    /**
     * To set course detail response data and then display it in view
     * @param url course detail API url
     * ***/
    fun requestCourseDetail(url: String)
}
