package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.repsoitory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class CourseRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) : CourseRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestCourse(url: String): List<Course> = withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi.getCourse(Urls.SUB_URL_CAMPAIGN_COURSE.format(url))
        val courseList = request.course
        courseList
    }
}
