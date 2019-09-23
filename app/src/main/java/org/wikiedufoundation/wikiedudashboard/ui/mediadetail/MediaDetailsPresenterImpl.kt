package org.wikiedufoundation.wikiedudashboard.ui.mediadetail

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import timber.log.Timber

class MediaDetailsPresenterImpl(private val mediaDetailsView: MediaDetailsContract.View, private val mediaDetailsProvider: MediaDetailsContract.Provider) : MediaDetailsContract.Presenter {

    override fun requestMediaDetails(cookies: String) {
        mediaDetailsView.showProgressBar(true)
        mediaDetailsProvider.requestMediaDetails(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                mediaDetailsView.showProgressBar(false)
                val mediaDetailResponse = o as MediaDetailsResponse
                Timber.d(mediaDetailResponse.toString())
                mediaDetailsView.setData(mediaDetailResponse)
            }

            override fun onFailure() {
                mediaDetailsView.showProgressBar(false)
                mediaDetailsView.showMessage("Unable to connect to server.")
            }
        })
    }
}
