package org.wikiedufoundation.wikiedudashboard.ui.campaign

import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit api service request call for campaign list
 ***/
class RetrofitCampaignListProvider(
        private val wikiEduDashboardApi: WikiEduDashboardApi
) : CampaignListContract.Provider {
//     = ProviderUtils.apiObject

    override fun requestCampaignList(cookies: String, presenterCallback: PresenterCallback<*>) {
        val campaignListResponseCall = wikiEduDashboardApi.getExploreCampaigns(cookies)
        campaignListResponseCall.enqueue(object : Callback<ExploreCampaignsResponse> {
            override fun onResponse(call: Call<ExploreCampaignsResponse>, response: Response<ExploreCampaignsResponse>) {
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ExploreCampaignsResponse>, t: Throwable) {
                presenterCallback.onFailure()
            }
        })
    }
}
