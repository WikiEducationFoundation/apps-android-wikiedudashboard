package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * [ExploreCampaignsResponse] response model
 * @constructor primary constructor to initialize campaigns list
 *
 * @property campaigns A list of campaign data
 * ***/
class ExploreCampaignsResponse(
    val campaigns: List<CampaignListData>
)
