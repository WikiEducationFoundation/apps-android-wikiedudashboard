package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User
import org.wikiedufoundation.wikiedudashboard.util.Urls

class StudentsRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) : StudentsRepository {

    override suspend fun requestStudentList(url: String): List<User> = withContext(Dispatchers.IO){
        val request = wikiEduDashboardApi
                .getStudentList(Urls.SUB_URL_COURSE_STUDENTLIST.format(url))
        val studentList = request.course.users
        studentList
    }
}