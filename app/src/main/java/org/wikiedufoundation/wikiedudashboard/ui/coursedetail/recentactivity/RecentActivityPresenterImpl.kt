package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Recent activity presenter to implement [requestRecentActivity] and display data in view
 * @constructor primary constructor with properties
 *
 * @property view view component for course detail
 * @property provider api service for course detail
 * ***/
class RecentActivityPresenterImpl(
    private val view: RecentActivityContract.View,
    private val provider: RecentActivityContract.Provider
) : RecentActivityContract.Presenter {

    override fun requestRecentActivity(url: String) {
        view.showProgressBar(true)
        provider.requestRecentActivity(url, object : PresenterCallback<RecentActivityResponse> {
            override fun onSuccess(recentActivityResponse: RecentActivityResponse) {
                view.showProgressBar(false)
                Timber.d(recentActivityResponse.toString())
                view.setData(recentActivityResponse)
            }

            override fun onFailure() {
                view.showProgressBar(false)
                view.showMessage("unable to connect to server.")
            }
        })
    }
}
