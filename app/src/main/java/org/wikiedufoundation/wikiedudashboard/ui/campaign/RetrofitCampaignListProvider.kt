package org.wikiedufoundation.wikiedudashboard.ui.campaign

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCampaignListProvider : CampaignListContract.Provider {
    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

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
