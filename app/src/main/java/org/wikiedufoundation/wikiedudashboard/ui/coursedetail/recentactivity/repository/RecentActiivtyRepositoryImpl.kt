package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls.SUB_URL_COURSE_RECENT

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class RecentActiivtyRepositoryImpl(
    private val wikiEduDashboardApi: WikiEduDashboardApi
) : RecentActivityRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun insertRecentActivity(url: String) : List<RecentActivity>
            = withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi
                .getRecentActivity(SUB_URL_COURSE_RECENT.format(url))
        val recentList = request.course.revisions
        recentList
    }
}