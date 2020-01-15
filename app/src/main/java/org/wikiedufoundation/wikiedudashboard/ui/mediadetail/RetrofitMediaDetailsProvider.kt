package org.wikiedufoundation.wikiedudashboard.ui.mediadetail

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardMediaApi
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that implements [requestMediaDetails]
 * ***/
class RetrofitMediaDetailsProvider(
    private val wikiEduDashboardApi: WikiEduDashboardMediaApi
) : MediaDetailsContract.Provider {

    override fun requestMediaDetails(cookies: String, presenterCallback: PresenterCallback<MediaDetailsResponse>) {
        val courseDetailResponseCall = wikiEduDashboardApi.getMediaDetailsFromCommons(cookies)
        courseDetailResponseCall.enqueue(object : Callback<MediaDetailsResponse> {
            override fun onResponse(call: Call<MediaDetailsResponse>, response: Response<MediaDetailsResponse>) {
                response.body()?.let { presenterCallback.onSuccess(it) }
            }

            override fun onFailure(call: Call<MediaDetailsResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
            }
        })
    }
}
