package org.wikiedufoundation.wikiedudashboard.ui.mediadetail

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * [MediaDetailsContract] interface that includes Model, View, & Presenter
 * for media details
 * ***/
interface MediaDetailsContract {

    /**
     * Implement [View.setData] to set media detail response data
     * ***/
    interface View : Progressive, Toaster {
        /**
         * Set media detail response data
         * @param data returned media details data
         * ***/
        fun setData(data: MediaDetailsResponse)
    }

    /**
     * Implement [Presenter.requestMediaDetails] to handle media details data
     * ***/
    interface Presenter {
        /**
         * Use [requestMediaDetails] to show cookies detail in logs
         * @param cookies media details cookies in String
         * ***/
        fun requestMediaDetails(cookies: String)
    }

    /**
     * Implement [Provider.requestMediaDetails] to perform Retrofit2 request call
     * ***/
    interface Provider {
        /**
         * Use [requestMediaDetails] to make http request call to get media details data
         *
         * @param cookies media details cookies
         * @param presenterCallback request call result callbacks
         * ***/
        fun requestMediaDetails(cookies: String, presenterCallback: PresenterCallback<*>)
    }
}
