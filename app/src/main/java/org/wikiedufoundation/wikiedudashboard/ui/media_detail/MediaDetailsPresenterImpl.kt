package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import android.util.Log

import org.wikiedufoundation.wikiedudashboard.ui.media_detail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

class MediaDetailsPresenterImpl(private val mediaDetailsView: MediaDetailsContract.View, private val mediaDetailsProvider: MediaDetailsContract.Provider) : MediaDetailsContract.Presenter {

    override fun requestMediaDetails(cookies: String) {
        mediaDetailsView.showProgressBar(true)
        mediaDetailsProvider.requestMediaDetails(cookies, object : PresenterCallback<Any> {
            override fun onSuccess(o: Any) {
                mediaDetailsView.showProgressBar(false)
                val mediaDetailResponse = o as MediaDetailsResponse
                Log.d("Presenter: ", mediaDetailResponse.toString())
                mediaDetailsView.setData(mediaDetailResponse)
            }

            override fun onFailure() {
                mediaDetailsView.showProgressBar(false)
                mediaDetailsView.showMessage("Unable to connect to server.")
            }
        })
    }
}
