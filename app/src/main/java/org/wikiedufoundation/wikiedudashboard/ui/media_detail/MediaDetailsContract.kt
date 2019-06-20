package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.media_detail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface MediaDetailsContract {

    interface View : Progressive, Toaster {
        fun setData(data: MediaDetailsResponse)
    }

    interface Presenter {
        fun requestMediaDetails(cookies: String)
    }

    interface Provider {
        fun requestMediaDetails(cookies: String, presenterCallback: PresenterCallback<*>)
    }
}
