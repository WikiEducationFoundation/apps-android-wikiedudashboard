package org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
interface DashboardRepository{


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCourseList: LiveData<List<CourseListData>>

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun getDashboardDetail(cookies: String)

}