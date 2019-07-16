package org.wikiedufoundation.wikiedudashboard.ui.profile

import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface ProfileContract {

    interface View : Progressive, Toaster {
        fun setData(data: ProfileResponse)
    }

    interface Presenter {
        fun requestProfile(cookies: String, username: String)
    }

    interface Provider {
        fun requestProfile(cookies: String, username: String, presenterCallback: PresenterCallback<*>)
    }
}
