package org.wikiedufoundation.wikiedudashboard.ui.dashboard

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that implements [requestCourseList] to get CourseList data
 * ***/
class RetrofitMyDashboardProvider(
    private val wikiEduDashboardApi: WikiEduDashboardApi
) : MyDashboardContract.Provider {

    override fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<MyDashboardResponse>) {
        val courseDetailResponseCall = wikiEduDashboardApi.getDashboardDetail(cookies)
        courseDetailResponseCall.enqueue(object : Callback<MyDashboardResponse> {
            override fun onResponse(call: Call<MyDashboardResponse>, response: Response<MyDashboardResponse>) {

                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<MyDashboardResponse>, t: Throwable) {
                presenterCallback.onFailure()
            }
        })
    }
}
