package org.wikiedufoundation.wikiedudashboard.data.localDatabse.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**
 * This creates an interface for
 * querying the database
 **/
interface ActiveCampaignDao{
    @Query("SELECT * from campaign_list_table ORDER BY id ASC")
    fun getAllCampaign() : LiveData<List<CampaignListData>>

    //more queries will be added
}