package org.wikiedufoundation.wikiedudashboard.ui.courselist.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class CourseListRepositoryImpl(
    private val wikiEduDashboardApi: WikiEduDashboardApi,
    private val courseListDao: CourseListDao
) : CourseListRepository {

    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    override fun allCourseList(): LiveData<List<CourseListData>> {
        return courseListDao.getAllCourses()
    }

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestCourseList(cookies: String) {
        withContext(Dispatchers.Main) {
            val request = wikiEduDashboardApi.getExploreCourses(cookies)
            val courseList = request.courses
            courseListDao.insertCourse(courseList)
        }
    }
}
