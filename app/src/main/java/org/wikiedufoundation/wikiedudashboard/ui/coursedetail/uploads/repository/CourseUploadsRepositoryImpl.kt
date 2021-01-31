package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class CourseUploadsRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) :
    CourseUploadsRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestCourseUploads(url: String): List<CourseUpload> =
        withContext(Dispatchers.IO) {
            val request = wikiEduDashboardApi.getCourseUploads(Urls.SUB_URL_COURSE_UPLOADS.format(url))
            val courseuploadList = request.course.uploads
            courseuploadList
        }
}
