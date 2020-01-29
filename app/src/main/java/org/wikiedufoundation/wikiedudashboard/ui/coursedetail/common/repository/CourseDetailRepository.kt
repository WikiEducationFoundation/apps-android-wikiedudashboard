package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail

/**
 *Declares CourseDetailRepository interface
 * */
interface CourseDetailRepository {

    /**
     *Creates a suspend function [requestCourseDetails]
     * */
    suspend fun requestCourseDetails(url : String) : CourseDetail
}