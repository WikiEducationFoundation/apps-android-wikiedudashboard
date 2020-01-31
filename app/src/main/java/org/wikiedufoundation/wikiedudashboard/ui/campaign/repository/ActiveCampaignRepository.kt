package org.wikiedufoundation.wikiedudashboard.ui.campaign.repository

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**
 * Declares the ActiveCampaignRepository interface
 * */
interface ActiveCampaignRepository {

    /**
     * This function gets all the campaigns
     **/
    fun allCapaignList(): LiveData<List<CampaignListData>>

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun requestCampaignList(cookies: String)
}