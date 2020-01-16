package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Retrofit http request for recent activities
 * ***/
class RetrofitRecentActivityProvider(
    private val wikiEduDashboardApi: WikiEduDashboardApi
) : RecentActivityContract.Provider {
//     = ProviderUtils.apiObject

    override fun requestRecentActivity(url: String, presenterCallback: PresenterCallback<RecentActivityResponse>) {
        val subUrl = "courses/$url/revisions.json"
        val articlesEditedResponseCall = wikiEduDashboardApi.getRecentActivity(subUrl)
        articlesEditedResponseCall.enqueue(object : Callback<RecentActivityResponse> {
            override fun onResponse(call: Call<RecentActivityResponse>, response: Response<RecentActivityResponse>) {

                Timber.d("${response.body()?.course} ")
                response.body()?.let { presenterCallback.onSuccess(it) }
            }

            override fun onFailure(call: Call<RecentActivityResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d("${t.message} ")
            }
        })
    }
}
