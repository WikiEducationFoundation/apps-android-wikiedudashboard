package org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository

import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
 * Declares the DashboardRepository interface
 * */
interface DashboardRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun requestDashboardDetail(cookies: String): List<CourseListData>
}
