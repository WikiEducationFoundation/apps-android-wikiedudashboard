package org.wikiedufoundation.wikiedudashboard.ui.profile

import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Presenter implementation for user profile
 * @constructor primary constructor
 *
 * @property view view to display profile data
 * @property provider Retrofit http request provider
 * ***/
class ProfilePresenterImpl(
        private val view: ProfileContract.View,
        private val provider: ProfileContract.Provider
) : ProfileContract.Presenter {

    override fun requestProfileDetails(username: String) {
        view.showProgressBar(true)
        provider.requestProfileDetails(username, object : PresenterCallback<ProfileDetailsResponse> {
            override fun onSuccess(profileDetailsResponse: Any?) {
                view.showProgressBar(false)
                val response = profileDetailsResponse as? ProfileDetailsResponse
                Timber.d(response.toString())
                view.setProfileData(response)

            }

            override fun onFailure() {
                view.showProgressBar(false)
                view.showMessage("unable to connect to server.")
            }
        })
    }

    override fun requestProfile(cookies: String, username: String) {
        view.showProgressBar(true)
        provider.requestProfile(cookies, username, object : PresenterCallback<ProfileResponse> {
            override fun onSuccess(profileResponse: Any?) {
                view.showProgressBar(false)

                val response = profileResponse as? ProfileResponse
                Timber.d(response.toString())
                view.setData(response)

            }

            override fun onFailure() {
                view.showProgressBar(false)
                view.showMessage("unable to connect to server.")
            }
        })

    }
}
