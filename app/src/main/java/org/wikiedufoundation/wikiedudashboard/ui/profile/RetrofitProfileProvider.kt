package org.wikiedufoundation.wikiedudashboard.ui.profile

import org.wikiedufoundation.wikiedudashboard.data.network.ProviderUtils
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Urls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Implementation of [requestProfile] & [requestProfileDetails] to perform http request
 * to get profile data
 * ***/
class RetrofitProfileProvider : ProfileContract.Provider {

    private val wikiEduDashboardApi: WikiEduDashboardApi = ProviderUtils.apiObject

    override fun requestProfile(cookies: String, username: String, presenterCallback: PresenterCallback<*>) {
        val articlesEditedResponseCall = wikiEduDashboardApi.getProfileResponse(cookies, username)
        articlesEditedResponseCall.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                Timber.d(response.body()?.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d(t.message + "")
            }
        })
    }

    override fun requestProfileDetails(username: String, presenterCallback: PresenterCallback<*>) {
        val url = Urls.BASE_URL + "users/" + username + "?format=json"
        val profileDetailsResponseCall = wikiEduDashboardApi.getProfileDetailsResponse(url)
        profileDetailsResponseCall.enqueue(object : Callback<ProfileDetailsResponse> {
            override fun onResponse(call: Call<ProfileDetailsResponse>, response: Response<ProfileDetailsResponse>) {
                Timber.d(response.body()?.toString() + "")
                presenterCallback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ProfileDetailsResponse>, t: Throwable) {
                presenterCallback.onFailure()
                t.printStackTrace()
                Timber.d(t.message + "")
            }
        })
    }

}
