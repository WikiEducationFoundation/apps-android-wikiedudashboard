package org.wikiedufoundation.wikiedudashboard.ui.courselist.provider

import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCourseListProvider : CourseListProvider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>) {
        val courseDetailResponseCall = wikiEduDashboardApi.getExploreCourses(cookies)
        courseDetailResponseCall.enqueue(object : Callback<ExploreCoursesResponse> {
            override fun onResponse(call: Call<ExploreCoursesResponse>, response: Response<ExploreCoursesResponse>) {
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ExploreCoursesResponse>, t: Throwable) {
                presenterCallback.onFailure()
            }
        })
    }
}
