package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardMediaApi
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.Query

/**
 * Declares the api as a private property in the constructor.
 * */
class MediaDetailsRepositoryImpl(private val wikiEduDashboardMediaApi: WikiEduDashboardMediaApi)
    : MediaDetailsRepository {


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestMediaDetails(cookies: String) : Query = withContext(Dispatchers.IO){
        val request = wikiEduDashboardMediaApi.getMediaDetailsFromCommons(cookies)
        val mediaDetails = request.query
        mediaDetails

    }



}