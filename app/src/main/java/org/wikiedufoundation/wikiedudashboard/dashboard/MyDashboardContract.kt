package org.wikiedufoundation.wikiedudashboard.dashboard

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.helper.Progressive
import org.wikiedufoundation.wikiedudashboard.helper.Toaster

interface MyDashboardContract {

    interface View : Progressive, Toaster {
        fun setData(data: MyDashboardResponse)
    }

    interface Presenter {
        fun requestDashboard(cookies: String)
    }

    interface Provider {
        fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<*>)
    }
}
