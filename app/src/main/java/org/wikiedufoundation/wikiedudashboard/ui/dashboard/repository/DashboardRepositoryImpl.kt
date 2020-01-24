package org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

class DashboardRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi,private val sharedPrefs: SharedPrefs): DashboardRepository {

    override suspend fun getDashboardDetail(cookies: String): List<CourseListData> = withContext(Dispatchers.IO){
        val request = wikiEduDashboardApi.getDashboardDetail(cookies).await()
        sharedPrefs.userName=request.user.userName
        request.currentCourses

    }
}