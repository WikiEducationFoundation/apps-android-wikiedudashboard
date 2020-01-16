package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * [CourseDetailProvider] interface to define [requestCourseDetail]
 * ***/
interface CourseDetailProvider {
    /**
     * Use [requestCourseDetail] to make http request for getting course details
     * @param url course detail API url
     * @param presenterCallback course detail presenter callback
    ***/
    fun requestCourseDetail(url: String, presenterCallback: PresenterCallback<CourseDetailResponse>)
}
