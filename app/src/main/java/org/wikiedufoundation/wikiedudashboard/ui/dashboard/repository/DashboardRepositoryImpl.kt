package org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class DashboardRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi,
                              private val courseListDao: CourseListDao, private val sharedPrefs: SharedPrefs) : DashboardRepository{

    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    override val allCourseList: LiveData<List<CourseListData>> = courseListDao.getAllCourses()



    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun getDashboardDetail(cookies: String) {
        withContext(Dispatchers.Main) {
            val request = wikiEduDashboardApi.getDashboardDetail(cookies).await()
            sharedPrefs.userName=request.user.userName
                val courseList = request.currentCourses
                courseListDao.insertCourse(courseList)
        }
    }
}