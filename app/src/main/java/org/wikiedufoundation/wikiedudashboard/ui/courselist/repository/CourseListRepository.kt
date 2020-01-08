package org.wikiedufoundation.wikiedudashboard.ui.campaign.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import timber.log.Timber

/**Declares the DAO as a private property in the constructor. Pass in the DAO
*instead of the whole database, because you only need access to the DAO*
 * */
class CourseListRepository(private val wikiEduDashboardApi: WikiEduDashboardApi,
                           private val courseListDao: CourseListDao){

    private var courseList = mutableListOf<CourseListData>()
    private var courseListLiveData = MutableLiveData<List<CourseListData>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCourseList : LiveData<List<CourseListData>> = courseListDao.getAllCourses()


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun getCourseListLiveData(cookies: String){
        coroutineScope.launch {
            val request = wikiEduDashboardApi.getExploreCourses(cookies)
            withContext(Dispatchers.Main) {
                try {
                    val mExploreCourse = request.await()
                    courseList = mExploreCourse.courses
                    courseListLiveData.value=courseList;
                    courseListDao.insertCourse(courseList)
                } catch (e: Exception) {
                    Timber.e(e.message.toString())
                } catch (e: Throwable) {
                    Timber.e(e.message.toString())
                }
            }
        }
    }
}