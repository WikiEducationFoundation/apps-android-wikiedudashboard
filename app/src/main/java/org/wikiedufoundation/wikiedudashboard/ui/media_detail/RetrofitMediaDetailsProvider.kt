package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.media_detail.data.MediaDetailsResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitMediaDetailsProvider : MediaDetailsContract.Provider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.commonsApiObject

    override fun requestMediaDetails(cookies: String, presenterCallback: PresenterCallback<*>) {
        val courseDetailResponseCall = wikiEduDashboardApi.getMediaDetailsFromCommons(cookies)
        courseDetailResponseCall.enqueue(object : Callback<MediaDetailsResponse> {
            override fun onResponse(call: Call<MediaDetailsResponse>, response: Response<MediaDetailsResponse>) {
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<MediaDetailsResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
            }
        })
    }
}
