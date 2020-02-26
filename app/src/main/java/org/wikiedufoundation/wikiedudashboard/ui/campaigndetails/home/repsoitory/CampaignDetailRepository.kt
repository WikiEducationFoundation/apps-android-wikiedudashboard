package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.repsoitory

import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data.CampaignDetails

/**
 *Declares CampaignDetailRepository interface
 * */
interface CampaignDetailRepository {

    /**
     *Creates a suspend function [requestCampaignDetails]
     * */
    suspend fun requestCampaignDetails(url: String): CampaignDetails
}