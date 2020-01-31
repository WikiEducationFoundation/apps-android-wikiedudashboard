package org.wikiedufoundation.wikiedudashboard.data.network

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Interface [WikiEduDashboardMediaApi]
 * to fetch the media endpoit with another url
 *
 **/
interface WikiEduDashboardMediaApi {

    /**
     * This API is used to fetch media details including category list, file uses, description,
     * license from Wikimedia Commons.
     ***/
    @GET("w/api.php?action=query&format=json&pageids=79744855&prop=globalusage|categories|imageinfo&iiprop=size|extmetadata|url&clshow=!hidden")
    suspend fun getMediaDetailsFromCommons(
        @Header("Cookie") sessionIdAndToken: String
    ): MediaDetailsResponse
}