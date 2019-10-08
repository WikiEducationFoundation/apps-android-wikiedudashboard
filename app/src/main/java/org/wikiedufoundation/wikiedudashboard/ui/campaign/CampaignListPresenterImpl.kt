package org.wikiedufoundation.wikiedudashboard.ui.campaign

import timber.log.Timber
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * To retrieve list of campaign data and format the data to display in view
 * @property myDashboardView CampaignListContract interface variable
 * @property myDashboardProvider CampaignListContract interface variable
 * ***/
class CampaignListPresenterImpl(private val myDashboardView: CampaignListContract.View, private val myDashboardProvider: CampaignListContract.Provider) : CampaignListContract.Presenter {
    override fun requestCampaignList(cookies: String) {
        myDashboardView.showProgressBar(true)
        myDashboardProvider.requestCampaignList(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                myDashboardView.showProgressBar(false)
                val exploreCampaignsResponse = o as ExploreCampaignsResponse
                Timber.d(exploreCampaignsResponse.toString())
                myDashboardView.setData(exploreCampaignsResponse)
            }

            override fun onFailure() {
                myDashboardView.showProgressBar(false)
                myDashboardView.showMessage("Unable to connect to server.")
            }
        })

    }
}
