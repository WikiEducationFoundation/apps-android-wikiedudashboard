package org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.courselist.dao.CourseListDao
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import timber.log.Timber

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class DashboardRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi,
                              private val courseListDao: CourseListDao, private val sharedPrefs: SharedPrefs)
    : DashboardRepository {


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */

    override val allCourseList: MutableLiveData<List<CourseListData>> by lazy {
        MutableLiveData<List<CourseListData>>()
    }

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/

    override suspend fun getDashboardDetail(cookies: String) {
        withContext(Dispatchers.IO) {
            val request:MyDashboardResponse = wikiEduDashboardApi.getDashboardDetail(cookies).await()
            if (sharedPrefs.userName == request.user.userName) {
                allCourseList.postValue(request.currentCourses)
            }else{
                Timber.d("No user found")
            }
        }
    }
}

