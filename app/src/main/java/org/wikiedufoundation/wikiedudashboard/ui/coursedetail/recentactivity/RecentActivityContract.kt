package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * Interface defining [View],[Presenter], & [Provider] interfaces
 * ***/
interface RecentActivityContract {

    interface View : Progressive, Toaster {
        /**
         * Set Recent Activity data
         * @param data recent activity data
         * ***/
        fun setData(data: RecentActivityResponse)
    }

    interface Presenter {
        /**
         * Recent activity http request
         * @param url api url
         * ***/
        fun requestRecentActivity(url: String)
    }

    interface Provider {
        /**
         * Recent activity http request for getting recent activity data
         * @param url recent activity data api url
         * @param presenterCallback presenter callback
         * ***/
        fun requestRecentActivity(url: String, presenterCallback: PresenterCallback<*>)
    }
}
