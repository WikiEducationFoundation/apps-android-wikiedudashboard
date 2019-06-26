package org.wikiedufoundation.wikiedudashboard.ui.campaign

import android.util.Log
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class CampaignListPresenterImpl(private val myDashboardView: CampaignListContract.View, private val myDashboardProvider: CampaignListContract.Provider) : CampaignListContract.Presenter {
    override fun requestCampaignList(cookies: String) {
        myDashboardView.showProgressBar(true)
        myDashboardProvider.requestCampaignList(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                myDashboardView.showProgressBar(false)
                val exploreCampaignsResponse = o as ExploreCampaignsResponse
                Log.d("Presenter: ", exploreCampaignsResponse.toString())
                myDashboardView.setData(exploreCampaignsResponse)
            }

            override fun onFailure() {
                myDashboardView.showProgressBar(false)
                myDashboardView.showMessage("Unable to connect to server.")
            }
        })

    }
}
