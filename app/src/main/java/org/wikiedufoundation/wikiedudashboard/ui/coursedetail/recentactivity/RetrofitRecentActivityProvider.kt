package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRecentActivityProvider : RecentActivityContract.Provider {
    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestRecentActivity(url: String, presenterCallback: PresenterCallback<*>) {
        val sub_url = "courses/$url/revisions.json"
        val articlesEditedResponseCall = wikiEduDashboardApi.getRecentActivity(sub_url)
        articlesEditedResponseCall.enqueue(object : Callback<RecentActivityResponse> {
            override fun onResponse(call: Call<RecentActivityResponse>, response: Response<RecentActivityResponse>) {
                Log.d("Success: ", response.body()!!.course.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<RecentActivityResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Log.d("Failure: ", t.message + "")
            }
        })
    }
}
