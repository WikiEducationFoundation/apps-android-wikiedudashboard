package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.repsoitory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data.CampaignDetails
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class CampaignDetailRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) :
    CampaignDetailRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    override suspend fun requestCampaignDetails(url: String): CampaignDetails =
        withContext(Dispatchers.IO) {
            val request = wikiEduDashboardApi
                .getCampaignDetail(Urls.SUB_URL_CAMPAIGN_DETAIL.format(url))
            val campaignDetail = request.campaign
            campaignDetail
        }
}
