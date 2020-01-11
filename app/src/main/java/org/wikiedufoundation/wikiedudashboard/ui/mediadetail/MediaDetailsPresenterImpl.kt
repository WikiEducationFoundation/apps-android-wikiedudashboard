package org.wikiedufoundation.wikiedudashboard.ui.mediadetail

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

/**
 * Class that implements [requestMediaDetails] to handle data and display in view
 * @constructor primary constructor
 *
 * @property mediaDetailsView variable of [MediaDetailsContract.View]
 * @property mediaDetailsProvider variable of [MediaDetailsContract.Provider]
 * ***/
class MediaDetailsPresenterImpl(
        private val mediaDetailsView: MediaDetailsContract.View,
        private val mediaDetailsProvider: MediaDetailsContract.Provider
) : MediaDetailsContract.Presenter {

    override fun requestMediaDetails(cookies: String) {
        mediaDetailsView.showProgressBar(true)
        mediaDetailsProvider.requestMediaDetails(cookies, object : PresenterCallback<MediaDetailsResponse> {
            override fun onSuccess(mediaDetailsResponse: MediaDetailsResponse) {
                mediaDetailsView.showProgressBar(false)
                Timber.d(mediaDetailsResponse.toString())
                mediaDetailsView.setData(mediaDetailsResponse)
            }

            override fun onFailure() {
                mediaDetailsView.showProgressBar(false)
                mediaDetailsView.showMessage("Unable to connect to server.")
            }
        })
    }
}
