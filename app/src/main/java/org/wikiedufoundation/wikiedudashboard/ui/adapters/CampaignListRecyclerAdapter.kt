package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import java.util.*

/**
 * RecyclerView adapter for campaign list data
 * ***/
class CampaignListRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter(layoutId) {

    private var campaigns: List<CampaignListData> = ArrayList()

    override fun getObjForPosition(position: Int): Any = campaigns[position]

    /**
     * Use [setData] to set a list of campaign data
     * @param campaigns A list of campaign data
     ***/
    fun setData(campaigns: List<CampaignListData>) {
        this.campaigns = campaigns
    }

    override fun getItemCount(): Int = campaigns.size


    fun onCampaignClicked(campaign: CampaignListData) {
        onClickListener(campaign.slug)
    }

}