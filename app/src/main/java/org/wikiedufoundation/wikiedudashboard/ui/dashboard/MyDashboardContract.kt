package org.wikiedufoundation.wikiedudashboard.ui.dashboard

import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface MyDashboardContract {

    interface View : Progressive, Toaster {
        fun setData(data: MyDashboardResponse)
    }

    interface Presenter {
        fun requestDashboard(cookies: String)
    }

    interface Provider {
        fun requestCourseList(cookies: String, presenterCallback: PresenterCallback<MyDashboardResponse>)
    }
}
