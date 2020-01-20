package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

/**
 * [ExploreCampaignsResponse] response model
 * @constructor primary constructor to initialize campaigns list
 *
 * @property campaigns A list of campaign data
 * ***/
data class ExploreCampaignsResponse(
    val campaigns: List<CampaignListData>
)
