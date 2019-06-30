package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface RecentActivityContract {

    interface View : Progressive, Toaster {
        fun setData(data: RecentActivityResponse)
    }

    interface Presenter {
        fun requestRecentActivity(url: String)
    }

    interface Provider {
        fun requestRecentActivity(url: String, presenterCallback: PresenterCallback<*>)
    }
}
