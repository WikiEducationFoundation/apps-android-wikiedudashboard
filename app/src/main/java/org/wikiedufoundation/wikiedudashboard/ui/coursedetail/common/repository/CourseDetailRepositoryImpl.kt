package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class CourseDetailRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) :
    CourseDetailRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestCourseDetails(url: String): CourseDetail =
        withContext(Dispatchers.IO) {
            val request = wikiEduDashboardApi
                .getCourseDetail(Urls.SUB_URL_COURSE_DETAIL.format(url))
            val courseDetail = request.course
            courseDetail
        }
}
