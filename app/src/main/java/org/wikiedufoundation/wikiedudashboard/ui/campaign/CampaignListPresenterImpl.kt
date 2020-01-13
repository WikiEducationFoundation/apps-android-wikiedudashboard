package org.wikiedufoundation.wikiedudashboard.ui.campaign

import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * To retrieve list of campaign data and format the data to display in view
 * @property myDashboardView CampaignListContract interface variable
 * @property myDashboardProvider CampaignListContract interface variable
 * ***/
class CampaignListPresenterImpl(
        private val myDashboardView: CampaignListContract.View,
        private val myDashboardProvider: CampaignListContract.Provider
) : CampaignListContract.Presenter {

    override fun requestCampaignList(cookies: String) {
        myDashboardView.showProgressBar(true)
        myDashboardProvider.requestCampaignList(cookies, object : PresenterCallback< ExploreCampaignsResponse> {
            override fun onSuccess(t:  ExploreCampaignsResponse) {
                myDashboardView.showProgressBar(false)
                Timber.d(t.toString())
                myDashboardView.setData(t)
            }

            override fun onFailure() {
                myDashboardView.showProgressBar(false)
                myDashboardView.showMessage("Unable to connect to server.")
            }
        })

    }
}
