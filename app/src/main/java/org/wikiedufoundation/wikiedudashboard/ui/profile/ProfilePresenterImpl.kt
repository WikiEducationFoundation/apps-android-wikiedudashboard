package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class ProfilePresenterImpl(private val view: ProfileContract.View,
                           private val provider: ProfileContract.Provider) : ProfileContract.Presenter {
    override fun requestProfile(cookies: String, username: String) {
        view.showProgressBar(true)
        provider.requestProfile(cookies,username, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any?) {
                view.showProgressBar(false)
                val response = o as ProfileResponse
                Log.d("Presenter: ", response.toString())
                view.setData(response)
            }

            override fun onFailure() {
                view.showProgressBar(false)
                view.showMessage("unable to connect to server.")
            }
        })

    }
}
