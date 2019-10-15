package org.wikiedufoundation.wikiedudashboard.ui.profile

import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * [ProfileContract] interface that includes provider, View, & Presenter for media details
 * ***/
interface ProfileContract {

    /**
     * Implement [View.setData] & [View.setProfileData]
     * ***/
    interface View : Progressive, Toaster {
        /**
         * Implement [setData] to set returned profile data from response
         *
         * @param data Profile data
         * ***/
        fun setData(data: ProfileResponse)

        /**
         * Implement [setProfileData] to set returned profile detail data from response
         *
         * @param data Profile detail data
         * ***/
        fun setProfileData(data: ProfileDetailsResponse)
    }

    /**
     * Implement [Presenter.requestProfileDetails] & [Presenter.requestProfile]
     * ***/
    interface Presenter {
        /**
         * Implement [requestProfileDetails] to handle profile detail data then display in view
         *
         * @param username put username to get profile details
         *  ***/
        fun requestProfileDetails(username: String)

        /**
         * Implement [requestProfile] to handle user profile data
         *
         * @param cookies user profile cookies
         * @param username username
         * ***/
        fun requestProfile(cookies: String, username: String)
    }

    /**
     * Implement [Provider.requestProfileDetails] and [Provider.requestProfile]
     * ***/
    interface Provider {
        /**
         * Implement [requestProfileDetails] to perform http request for profile details data
         *
         * @param username username in String
         * @param presenterCallback http request result callback
         * ***/
        fun requestProfileDetails(username: String, presenterCallback: PresenterCallback<*>)

        /**
         * Implement [requestProfile] to perform http request for profile data
         *
         * @param cookies user profile cookies in String
         * @param username username in String
         * @param presenterCallback http request result callback
         * ***/
        fun requestProfile(cookies: String, username: String, presenterCallback: PresenterCallback<*>)
    }
}
