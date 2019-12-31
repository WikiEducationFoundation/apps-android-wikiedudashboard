package org.wikiedufoundation.wikiedudashboard.data.localDatabse.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse

/**
 * This creates an interface for
 * querying the database
 **/
@Dao
interface ActiveCampaignDao{

    /**
     * This get all the campaigns
     *
     **/
    @Query("SELECT * from campaign_list_table ORDER BY id ASC")
    fun getAllCampaign() : LiveData<List<CampaignListData>>

    //more queries will be added
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCampaign(campaignList : CampaignListData)

    @Query("DELETE FROM campaign_list_table")
    suspend fun deleteAllCampaign()
}