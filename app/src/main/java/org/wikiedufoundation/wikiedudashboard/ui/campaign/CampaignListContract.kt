package org.wikiedufoundation.wikiedudashboard.ui.campaign

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface CampaignListContract {

    interface View : Progressive, Toaster {
        fun setData(data: ExploreCampaignsResponse)
    }

    interface Presenter {
        fun requestCampaignList(cookies: String)
    }

    interface Provider {
        fun requestCampaignList(cookies: String, presenterCallback: PresenterCallback<*>)
    }
}
