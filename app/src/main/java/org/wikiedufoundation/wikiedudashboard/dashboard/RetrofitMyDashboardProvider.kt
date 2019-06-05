package org.wikiedufoundation.wikiedudashboard.dashboard

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitMyDashboardProvider : MyDashboardContract.Provider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>) {
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
