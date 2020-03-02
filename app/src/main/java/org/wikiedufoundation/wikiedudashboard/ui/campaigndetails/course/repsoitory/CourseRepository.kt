package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.repsoitory

import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course

/**
 *Declares CourseRepository interface
 * */
interface CourseRepository {

    /**
     *Creates a suspend function [requestCourse]
     * */

    suspend fun requestCourse(url: String): List<Course>
}