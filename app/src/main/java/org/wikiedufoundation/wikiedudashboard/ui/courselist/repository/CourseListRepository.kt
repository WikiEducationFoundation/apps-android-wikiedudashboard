package org.wikiedufoundation.wikiedudashboard.ui.courselist.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import timber.log.Timber
import java.lang.Exception

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class CourseListRepository(private val wikiEduDashboardApi: WikiEduDashboardApi,
                           private val courseListDao: CourseListDao) {

    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCourseList: LiveData<List<CourseListData>> = courseListDao.getAllCourses()


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun getCourseList(cookies: String) {
        withContext(Dispatchers.Main) {
            val request = wikiEduDashboardApi.getExploreCourses(cookies).await()
            val courseList = request.courses
            courseListDao.insertCourse(courseList)
        }

    }
}