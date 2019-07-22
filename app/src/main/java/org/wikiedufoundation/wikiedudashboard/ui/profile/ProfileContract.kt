package org.wikiedufoundation.wikiedudashboard.ui.profile

import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

interface ProfileContract {

    interface View : Progressive, Toaster {
        fun setData(data: ProfileResponse)
        fun setProfileData(data: ProfileDetailsResponse)
    }

    interface Presenter {
        fun requestProfileDetails(username: String)
        fun requestProfile(cookies: String, username: String)
    }

    interface Provider {
        fun requestProfileDetails(username: String, presenterCallback: PresenterCallback<*>)
        fun requestProfile(cookies: String, username: String, presenterCallback: PresenterCallback<*>)
    }
}
