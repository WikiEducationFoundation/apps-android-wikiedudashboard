package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.repsoitory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.data.Students
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class StudentRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi)
    : StudentRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestStudent(url: String): List<Students> =
            withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi
                .getStudent(Urls.SUB_URL_CAMPAIGN_STUDENT.format(url))
        val studentlist = request.users
        studentlist
    }
}